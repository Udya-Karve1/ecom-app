package com.sk.rk.service;

import com.sk.rk.events.OrderCompletedEvent;
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

    @Autowired
    @Qualifier(value = "order_completed_template")
    private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate2;

    public void publishMessageCreated(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send(orderCreatedTopic, orderCreatedEvent);
    }

    public void publishMessageCompleted(OrderCompletedEvent orderCompletedEvent) {
        kafkaTemplate2.send(orderCompletedTopic, orderCompletedEvent);
    }
}
