package com.sk.rk.service;

import com.sk.rk.events.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private WebClient webClient;

    public Object getBalance(Long customerId) {
        return webClient
                .get()
                .uri("/customer/v1/api/balance/" + customerId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Object debit(PaymentRequestDTO requestDTO) {
        return webClient
                .post()
                .uri("/customer/v1/api/balance/debit")
                .body(Mono.just(requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Object credit(PaymentRequestDTO requestDTO) {

        return webClient
                .post()
                .uri("/customer/v1/api/balance/credit")
                .body(Mono.just(requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
