package com.sk.rk.order.service;

import com.sk.rk.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {

    @Autowired
    @Qualifier(value = "order_created_template")
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${spring.topic.order-create}")
    private String orderCreatedTopic;

    @Value("${spring.topic.order-completed}")
    private String orderCompletedTopic;


    public void publishMessageCreated(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send(orderCreatedTopic, orderCreatedEvent);
    }
}
