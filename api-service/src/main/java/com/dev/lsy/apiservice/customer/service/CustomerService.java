package com.dev.lsy.apiservice.customer.service;

import com.dev.lsy.apiservice.cmmn.Response;
import com.dev.lsy.apiservice.cmmn.ResultType;
import com.dev.lsy.apiservice.customer.domain.Customer;
import com.dev.lsy.apiservice.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerMapper customerMapper;

    /**
     * 고객 목록 조회
     * @return
     * @throws Exception
     */
    public ResponseEntity<?> getCustomerList() throws Exception{

        Response response = new Response();

        List<Customer> customerList = customerMapper.selectCustomerList();

        if (customerList != null && customerList.size() > 0) {
            response.setCode(ResultType.SUCCESS.getCode());
            response.setMsg(ResultType.SUCCESS.getMsg());
            response.setList(customerList);
        } else {
            response.setList(Collections.EMPTY_LIST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
