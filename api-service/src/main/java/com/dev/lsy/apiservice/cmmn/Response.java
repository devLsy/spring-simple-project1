package com.dev.lsy.apiservice.cmmn;

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
public class Response<T> {

    private String code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String err;
    private List<T> list;
}
