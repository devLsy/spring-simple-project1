package com.dev.lsy.batchservice.batch.tasklet;

import com.dev.lsy.batchservice.batch.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
public class CustomTasklet implements Tasklet {

//    @Value("${rest-api.url}")
//    private URI url;
    private String url;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        url = "http://localhost:9090/customer";
        Customer response = restTemplate.getForObject(url, Customer.class);

        log.info("url request~");
        log.info("response ==> [{}]", response);

        return (response != null ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE);
    }
}
