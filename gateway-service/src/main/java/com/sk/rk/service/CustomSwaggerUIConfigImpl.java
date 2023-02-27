package com.sk.rk.service;

import com.sk.rk.model.GatewaySwagger;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomSwaggerUIConfigImpl extends AbstractSwaggerUiConfigProperties {

    private GatewayService gatewayService;


    public CustomSwaggerUIConfigImpl(GatewayService gatewayService) {
        super();
        this.gatewayService = gatewayService;
        this.setUrls(prepareSwaggerUrl(gatewayService.getSwaggerList()));
    }

    private Set<SwaggerUrl> prepareSwaggerUrl(List<GatewaySwagger> swaggerList) {
        return swaggerList.stream().map(swagger->
                (new SwaggerUrl(swagger.getServiceName(), swagger.getServiceUrl(), swagger.getServiceName()))
        ).collect(Collectors.toSet());
    }
}
