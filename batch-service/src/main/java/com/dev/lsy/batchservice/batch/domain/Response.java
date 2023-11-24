package com.dev.lsy.batchservice.batch.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String err;
    private List<?> list;
}
