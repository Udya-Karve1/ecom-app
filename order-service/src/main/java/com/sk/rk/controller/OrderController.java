package com.sk.rk.controller;

import com.sk.rk.service.OrderService;
import com.sk.rk.exception.BaseException;
import com.sk.rk.model.AddOrderRequest;
import com.sk.rk.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody AddOrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}/{order-status}")
    public ResponseEntity<Order> addOrder(
            @PathVariable("order-id") Long orderId,
            @PathVariable("order-status") String orderStatus
    ) throws BaseException {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, orderStatus), HttpStatus.OK);
    }

}
