package com.sk.rk.customer.controller;

import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.common.exception.BaseException;
import com.sk.rk.customer.model.entity.Customer;
import com.sk.rk.customer.model.request.AddCustomer;
import com.sk.rk.customer.model.request.UpdateCustomer;
import com.sk.rk.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/customer")
@Tag(name = "CustomerController", description = "Customer related endpoints.")
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customer-id}")
    @Operation(summary = "Get customer by ID", description = "Get customer POJO by providing a numeric CustomerId as a path variable.")
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
    public ResponseEntity<String> deleteCustomer(@PathVariable("customer-id") Long customerId) {
        this.customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
    }

    @GetMapping("/balance/{customer-id}")
    public ResponseEntity<Map<String, Object>> getBalance(@PathVariable("customer-id")Long customerId) throws BaseException {
        log.info("customer controller balance called.......");
        return new ResponseEntity<>(customerService.getBalance(customerId), HttpStatus.OK);
    }
    @PostMapping("/balance/debit")
    public ResponseEntity<Map<String, String>> debitBalance(@RequestBody PaymentRequestDTO requestDTO) throws BaseException {
        log.info("customer controller debit called.......");
        return new ResponseEntity<>(customerService.debitBalance(requestDTO), HttpStatus.OK);
    }

    @PostMapping("/balance/credit")
    public ResponseEntity<Map<String, String>> creditBalance(@RequestBody PaymentRequestDTO requestDTO) throws BaseException {
        log.info("customer controller credit called.......");
        return new ResponseEntity<>(customerService.creditBalance(requestDTO), HttpStatus.OK);
    }
}
