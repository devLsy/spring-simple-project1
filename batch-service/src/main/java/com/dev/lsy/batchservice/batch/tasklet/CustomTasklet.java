package com.dev.lsy.batchservice.batch.tasklet;

import com.dev.lsy.batchservice.batch.domain.Customer;
import com.dev.lsy.batchservice.batch.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomTasklet implements Tasklet {

//    @Value("${rest-api.url}")
    private String url = "http://localhost:9090/customer";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        Response<Customer> response = restTemplate.getForObject(url, Response.class);

        List<Customer> customerList = response.getList();

        Customer newCustomer = new Customer();
        List<Customer> newCustomerList = new ArrayList<>();

        log.info("list ==> [{}]", customerList);

//        List<Customer> newCustomerList = customerList.stream()
//                .map(item -> new Customer(item.getId(), item.getFirstName() + "수정", item.getLastName() + "수정", item.getBirthdate()))
//                .collect(Collectors.toList());
//        for (Customer customer : customerList) {
//            newCustomer.setFirstName(customer.getFirstName());
//            newCustomer.setLastName(customer.getLastName());
//            newCustomer.setBirthdate(customer.getBirthdate());
//            newCustomerList.add(newCustomer);
//        }
        return (response != null ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE);
    }
}
