package com.sk.rk.config;

import com.sk.rk.service.ServiceRouteService;
import com.sk.rk.service.impl.ServicePathRouteLocatorImpl;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WebConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public List<ServiceInstance> applicationList() {
        final List<ServiceInstance> services = new ArrayList<>();

        return discoveryClient.getServices().stream().map(serviceIds->
            discoveryClient.getInstances(serviceIds).get(0)
        ).collect(Collectors.toList());
    }

    @Bean
    public RouteLocator routeLocator(ServiceRouteService apiRouteService,
                                     RouteLocatorBuilder routeLocatorBuilder) {
        return new ServicePathRouteLocatorImpl(apiRouteService, routeLocatorBuilder);
    }
}
