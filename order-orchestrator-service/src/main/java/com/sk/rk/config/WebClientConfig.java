package com.sk.rk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebClientConfig {

    @Bean(name = "payment")
    @LoadBalanced
    public WebClient paymentClient(@Value("${service.endpoints.payment}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

    @Bean(name = "inventory")
    @LoadBalanced
    public WebClient inventoryClient(@Value("${service.endpoints.inventory}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }
}

class InstanceSupplier implements ServiceInstanceListSupplier {
    private final String serviceId;

    public InstanceSupplier(String serviceId) {
        this.serviceId = serviceId;
    }
    @Override
    public String getServiceId() {
        return this.serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8080, false)
                        ));
    }
}
