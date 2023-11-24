package com.dev.lsy.apiservice.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "mappers/customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String birthdate;
}
