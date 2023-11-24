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
import java.util.List;

@Slf4j
public class CustomTasklet implements Tasklet {

//    @Value("${rest-api.url}")
    private String url = "http://localhost:9090/customer";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        Response response = restTemplate.getForObject(url, Response.class);
//        ResponseEntity<List<Customer>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Customer>>() {});

        log.info("url request ==> [{}]", url);
        log.info("response ==> [{}]", response.getList());

        return (response != null ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE);
    }
}
