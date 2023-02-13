package com.sk.rk.service;

import com.sk.rk.events.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class PaymentService {

/*
    @Autowired
    @Qualifier(value = "customerWebClient")
    private WebClient webClient;
*/

    @Autowired
    private WebClient.Builder loadBalancedClient;

    public Object getBalance(Long customerId) {
        return loadBalancedClient.build().get().uri("http://CUSTOMER-SERVICE/v1/api/customer/balance/" + customerId).retrieve().bodyToMono(Map.class);
    }

    public Object debit(PaymentRequestDTO requestDTO) {
        return loadBalancedClient.build()
                .post()
                .uri("http://CUSTOMER-SERVICE/v1/api/customer/balance/debit")
                .body(Mono.just(requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Object credit(PaymentRequestDTO requestDTO) {

        return loadBalancedClient.build()
                .post()
                .uri("http://CUSTOMER-SERVICE/v1/api/customer/balance/credit")
                .body(Mono.just(requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(Map.class);
    }

}
