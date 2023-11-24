package com.dev.lsy.apiservice.customer.web;

import com.dev.lsy.apiservice.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ApiRestController {

    private final CustomerService customerService;

    /**
     * 고객 목록 조회
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public ResponseEntity<?> main() throws Exception {
        return customerService.getCustomerList();
    }
}
