package com.dev.lsy.batchservice.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
}
