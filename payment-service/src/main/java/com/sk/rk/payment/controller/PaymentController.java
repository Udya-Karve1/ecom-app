package com.sk.rk.payment.controller;

import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment/v1/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{customer-id}")
    public Object getCustomerBalance(@PathVariable("customer-id")Long customerId) {
        log.info("payment endpoint called ..............");
        return paymentService.getBalance(customerId);
    }

    @PostMapping("/debit")
    public Object debitPayment(@RequestBody PaymentRequestDTO requestDTO) {
        log.info("payment controller debit called .........");
        return paymentService.debit(requestDTO);
    }

    @PostMapping("/credit")
    public Object creditPayment(@RequestBody PaymentRequestDTO requestDTO) {
        log.info("payment controller credit called .........");
        return paymentService.credit(requestDTO);
    }



}
