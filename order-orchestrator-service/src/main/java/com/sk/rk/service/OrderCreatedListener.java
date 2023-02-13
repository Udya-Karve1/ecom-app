package com.sk.rk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.rk.events.OrchestratorRequestDTO;
import com.sk.rk.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.management.monitor.MonitorNotification;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

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

        Mono<OrderCreatedEvent> orderCreatedMono = Mono.just(order);

        OrchestratorRequestDTO requestDTO = createEntityOrchestratorRequestDTO(order);


        orchestratorService.orderProduct(createEntityOrchestratorRequestDTO(order));
    }

    private OrchestratorRequestDTO createEntityOrchestratorRequestDTO(OrderCreatedEvent order) {
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setOrderId(order.getOrderId());
        requestDTO.setCustomerId(order.getCustomerId());
        requestDTO.setProductId(order.getProductId());
        requestDTO.setAmount(new BigDecimal(100.25));

        return requestDTO;
    }


}
