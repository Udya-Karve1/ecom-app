package com.sk.rk.config;



import com.sk.rk.service.ApiRoutePathLocatorImpl;

import com.sk.rk.service.CustomSwaggerUIConfigImpl;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sk.rk.service.GatewayService;
import org.springframework.context.annotation.Primary;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class WebConfig {

    @Autowired
    RouteDefinitionLocator locator;

    @Autowired
    private GatewayService apiRouteService;




    @Bean
    public RouteLocator routeLocator(GatewayService apiRouteService,
                                     RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiRoutePathLocatorImpl(apiRouteService, routeLocatorBuilder);
    }






    @Primary
    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
        ApplicationContext applicationContext = GatewayConfig.getApplicationContext();
        SwaggerUiConfigParameters swaggerUiConfigParameters = (SwaggerUiConfigParameters)applicationContext.getBean("org.springdoc.core.properties.SwaggerUiConfigParameters");
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrlSet = apiRouteService.getSwaggerList().stream().map(swagger-> {
            return new AbstractSwaggerUiConfigProperties.SwaggerUrl(swagger.getServiceName(), swagger.getServiceUrl(), swagger.getServiceName());
        }).collect(Collectors.toSet());

        swaggerUiConfigParameters.setUrls(swaggerUrlSet);

        return swaggerUiConfigParameters;
    }

    @Bean
    @Primary
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        ApplicationContext applicationContext = GatewayConfig.getApplicationContext();
        SwaggerUiConfigProperties swaggerUiConfigProperties = (SwaggerUiConfigProperties)applicationContext.getBean("org.springdoc.core.properties.SwaggerUiConfigProperties");
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrlSet = apiRouteService.getSwaggerList().stream().map(swagger-> {
            return new AbstractSwaggerUiConfigProperties.SwaggerUrl(swagger.getServiceName(), swagger.getServiceUrl(), swagger.getServiceName());
        }).collect(Collectors.toSet());

        swaggerUiConfigProperties.setUrls(swaggerUrlSet);

        return swaggerUiConfigProperties;
    }

    @Bean
    public List<GroupedOpenApi> apis() {

        List<GroupedOpenApi> groups = new ArrayList<>();

        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-SERVICE")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-SERVICE", "");
            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        });

        return groups;
    }
}
