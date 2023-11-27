package com.dev.lsy.batchservice.batch.tasklet;

import com.dev.lsy.batchservice.batch.domain.Customer;
import com.dev.lsy.batchservice.batch.domain.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CustomTasklet implements Tasklet {

    private final JdbcTemplate jdbcTemplate;
    private String url = "http://localhost:9090/customer";

    @Override
    @Transactional
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

//        Response<Customer> response = restTemplate.getForObject(url, Response.class);

        ResponseEntity<Response<Customer>> response = restTemplate.exchange(
                       url,
                       HttpMethod.GET,
                       null,
                       new ParameterizedTypeReference<Response<Customer>>() {}
               );

        List<Customer> customerList = response.getBody().getList();

        Customer newCustomer = new Customer();
        List<Customer> newCustomerList = new ArrayList<>();

        log.info("list ==> [{}]", customerList);

        for (Customer customer : customerList) {
            newCustomer = new Customer();
            newCustomer.setId(customer.getId());
            newCustomer.setFirstName(customer.getFirstName() + "수정");
            newCustomer.setLastName(customer.getLastName() + "수정");
            newCustomer.setBirthDate(customer.getBirthDate());
            newCustomerList.add(newCustomer);
        }

        // customer3 테이블에 insert
        for (Customer customer : newCustomerList) {
            jdbcTemplate.update("INSERT INTO customer3 (id, first_name, last_name, birthdate) VALUES (?, ?, ?, ?)",
                    customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getBirthDate());
        }

        log.info("newCustomerList ==> [{}]", newCustomerList);

        return (response != null ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE);
    }
}
