package com.dev.lsy.apiservice.customer.repository;

import com.dev.lsy.apiservice.customer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
