package com.dev.lsy.apiservice.cmmn;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultType {

    SUCCESS("1", "성공"),
    FAIL("2", "실패");

    private final String code;
    private final String msg;
}
