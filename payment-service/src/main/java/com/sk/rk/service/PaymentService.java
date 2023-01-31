package com.sk.rk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
     /*   return  webClient.get().uri("http://CUSTOMER-SERVICE/v1/api/customer/balance/" + customerId)
                .retrieve();*/
    }
}
