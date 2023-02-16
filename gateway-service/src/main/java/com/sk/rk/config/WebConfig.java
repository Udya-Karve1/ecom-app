package com.sk.rk.config;


import com.sk.rk.service.ApiRoutePathLocatorImpl;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sk.rk.model.GatewaySwagger;

import java.util.ArrayList;
import java.util.List;
import com.sk.rk.service.GatewayService;
@Configuration
public class WebConfig {

    @Autowired
    RouteDefinitionLocator locator;

    @Autowired
    private GatewayService gatewayService;

    @Bean
    public RouteLocator routeLocator(GatewayService apiRouteService,
                                     RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiRoutePathLocatorImpl(apiRouteService, routeLocatorBuilder);
    }


    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        //RouteLocator locator = routeLocator(gatewayService, routeLocatorBuilder);

        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-SERVICE")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-service", "");
            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        });
        return groups;
    }


}
