package com.dev.lsy.apiservice.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void 고객목록조회() throws Exception {
        ResponseEntity<?> customerList = customerService.getCustomerList();
        log.info("item ==> [{}]", customerList);
    }

}