package com.dev.lsy.apiservice.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiRestController {

    @GetMapping("/")
    public String main() {  
        log.info("안녕~");
        return "hello@@@@@@@@@";
    }
}
