package com.sk.rk.config;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sk.rk.model.GatewaySwagger;

import java.util.ArrayList;
import java.util.List;
import com.sk.rk.service.GatewayService;
@Configuration
public class WebConfig {

    @Autowired
    private RouteDefinitionLocator locator;

    @Autowired
    private GatewayService gatewayService;

    @Bean
    @ConditionalOnBean(value = GatewayService.class)
    public List<GroupedOpenApi> swaggerGroup() {
        List<GatewaySwagger> swaggerList = gatewayService.getSwaggerList();
        List<GroupedOpenApi> groups = new ArrayList<>();
        swaggerList.stream().forEach(swagger->
            groups.add(GroupedOpenApi.builder().pathsToMatch(swagger.getServiceUrl()).group(swagger.getServiceName()).build())
        );

        return groups;
    }

}
