package com.sk.rk.service;

import com.sk.rk.events.OrderAddUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, OrderAddUpdateRequest> kafkaTemplate;

    public void publicMessage(OrderAddUpdateRequest orderAddUpdateRequest) {
        kafkaTemplate.send("OrderAddUpdateTopic", orderAddUpdateRequest);
    }
}
