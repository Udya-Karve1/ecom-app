package com.sk.rk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.rk.events.OrchestratorRequestDTO;
import com.sk.rk.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class OrderCreatedListener {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrchestratorService orchestratorService;


    @KafkaListener(topics = "${spring.topic.order-create}", groupId = "${spring.group-id.order-created}")
    public void consumer(OrderCreatedEvent order) throws JsonProcessingException {
        log.info("consumer: {}", objectMapper.writeValueAsString(order));
        //orchestratorService.orderProduct(createEntityOrchestratorRequestDTO(order));
        orchestratorService.orderProductMono(createEntityOrchestratorRequestDTO(order));
    }

    private OrchestratorRequestDTO createEntityOrchestratorRequestDTO(OrderCreatedEvent order) {
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setOrderId(order.getOrderId());
        requestDTO.setCustomerId(order.getCustomerId());
        requestDTO.setProductId(order.getProductId());
        requestDTO.setAmount(Double.valueOf(100.25));

        return requestDTO;
    }
}
