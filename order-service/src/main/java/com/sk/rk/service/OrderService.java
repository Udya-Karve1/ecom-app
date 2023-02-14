package com.sk.rk.service;


import org.springframework.stereotype.Service;


@Service
public class OrderService {
/*

    @Autowired
    private OrderRepository repository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Order saveOrder(AddOrderRequest orderRequest) {
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .customerId(orderRequest.getCustomerId())
                .orderStatus("Created")
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .dateUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        Order orderCreated = repository.save(order);
        kafkaProducer.publishMessageCreated(createOrderCreatedEvent(order));
        KafkaProducer.publishMessageCompleted();

        return order;
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

    private OrderCompleted
*/

}
