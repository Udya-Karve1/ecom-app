package com.sk.rk.service;

import com.sk.rk.exception.BaseException;
import com.sk.rk.model.AddOrderRequest;
import com.sk.rk.model.Order;
import com.sk.rk.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order saveOrder(AddOrderRequest orderRequest) {
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .customerId(orderRequest.getCustomerId())
                .orderStatus("Created")
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .dateUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        return repository.save(order);
    }


    private Order getOrderById(Long orderId) throws BaseException {
        return repository.findById(orderId).orElseThrow(
                ()->new BaseException(400, "Order not found."));
    }

    public Order updateOrderStatus(Long orderId, String orderStatus) throws BaseException {
        Order order = getOrderById(orderId);
        order.setOrderStatus(orderStatus);
        order.setDateUpdated(new Timestamp(System.currentTimeMillis()));

        return repository.save(order);
    }

}
