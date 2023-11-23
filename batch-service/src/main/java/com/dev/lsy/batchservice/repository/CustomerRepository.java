package com.dev.lsy.batchservice.repository;

import com.dev.lsy.batchservice.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
