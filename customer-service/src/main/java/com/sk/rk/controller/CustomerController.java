package com.sk.rk.controller;

import com.sk.rk.exception.BaseException;
import com.sk.rk.model.entity.Customer;
import com.sk.rk.model.request.AddCustomer;
import com.sk.rk.model.request.UpdateCustomer;
import com.sk.rk.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customer-id")Long customerId) throws BaseException {
        return new ResponseEntity<>(this.customerService.getCustomerById(customerId) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody AddCustomer customer) throws BaseException {
        return new ResponseEntity<>(this.customerService.addCustomer(customer), HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody UpdateCustomer customer) throws BaseException {
        return new ResponseEntity<>(this.customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customer-id") Long customerId) throws BaseException {
        this.customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
    }

    @GetMapping("/balance/{customer-id}")
    public ResponseEntity getBalance(@PathVariable("customer-id")Long customerId) throws BaseException {
        return new ResponseEntity<>(customerService.getBalance(customerId), HttpStatus.OK);
    }
}
