package com.dev.lsy.apiservice.customer.mapper;

import com.dev.lsy.apiservice.customer.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /* 고객 목록 조회 */
    List<Customer> selectCustomerList();
}
