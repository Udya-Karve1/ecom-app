package com.sk.rk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sk.rk.model.CustomerOrder;
import com.sk.rk.model.CustomerOrderRequest;
import com.sk.rk.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer-order/v1/api")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public ResponseEntity<List<CustomerOrder>> searchCustomerOrder(@RequestBody CustomerOrderRequest request) throws JsonProcessingException {
        return new ResponseEntity<>(customerOrderService.searchCustomerOrder(request), HttpStatus.OK);
    }
}
