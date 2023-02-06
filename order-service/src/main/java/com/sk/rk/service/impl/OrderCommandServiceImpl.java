package com.sk.rk.service.impl;

import com.sk.rk.events.OrderCreatedEvent;
import com.sk.rk.model.AddOrder;
import com.sk.rk.model.Order;
import com.sk.rk.model.UpdateOrder;
import com.sk.rk.repository.OrderRepository;
import com.sk.rk.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.rk.service.KafkaOrderProducer;
import com.sk.rk.events.OrderCompletedEvent;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaOrderProducer kafkaOrderProducer;


    public Order createOrder(AddOrder orderRequest) {
        Order order = Order.builder()
                .customerId(orderRequest.getCustomerId())
                .productId(orderRequest.getProductId())
                .dateCreated(orderRequest.getDateCreated())
                .dateUpdated(orderRequest.getDateUpdated())
                .orderStatus(orderRequest.getOrderStatus())
                .build();

        order = orderRepository.save(order);
        kafkaOrderProducer.publishMessageCreated(createOrderCreatedEvent(order));
        kafkaOrderProducer.publishMessageCompleted(createOrderCompletedEvent(order));
        return order;
    }

    @Override
    public Order modifiedOrder(UpdateOrder order) {
        return null;
    }

    @Override
    public void updateOrderStatus(Long orderId, String orderStatus) {

    }


    private OrderCreatedEvent createOrderCreatedEvent(Order order) {
        OrderCreatedEvent createdEvent = new OrderCreatedEvent();
        createdEvent.setOrderId(order.getOrderId());
        createdEvent.setDateCreated(order.getDateCreated());
        createdEvent.setDateUpdated(order.getDateUpdated());
        createdEvent.setOrderStatus(order.getOrderStatus());
        createdEvent.setProductId(order.getProductId());
        createdEvent.setCustomerId(order.getCustomerId());

        return createdEvent;
    }

    private OrderCompletedEvent createOrderCompletedEvent(Order order) {
        OrderCompletedEvent completedEvent = new OrderCompletedEvent();
        completedEvent.setOrderId(order.getOrderId());
        completedEvent.setDateCreated(order.getDateCreated());
        completedEvent.setDateUpdated(order.getDateUpdated());
        completedEvent.setOrderStatus(order.getOrderStatus());
        completedEvent.setProductId(order.getProductId());
        completedEvent.setCustomerId(order.getCustomerId());

        return completedEvent;
    }


}
