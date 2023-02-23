package com.sk.rk.controller;

import com.sk.rk.model.AddOrder;

import com.sk.rk.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sk.rk.service.OrderCommandService;

@RestController
@RequestMapping("/order/v1/api")
public class OrderController {

    @Autowired
    private OrderCommandService orderService;


    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody AddOrder orderRequest) {
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}/{order-status}")
    public ResponseEntity<String> addOrder(
            @PathVariable("order-id") Long orderId,
            @PathVariable("order-status") String orderStatus
    ) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>("Status updated successfully.", HttpStatus.OK);
    }

}
