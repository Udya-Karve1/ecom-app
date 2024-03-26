package com.sk.rk.gateway.controller;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class SwaggerController {
    private final DiscoveryClient discoveryClient;

    private static final List<String> KUBE_SERVICES = Arrays.asList("kubernetes", "kube-dns", "prometheus-kube-prometheus-kubelet");

    public SwaggerController(final DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/v3/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig(ServerHttpRequest serverHttpRequest) throws URISyntaxException {
        URI uri = serverHttpRequest.getURI();
        String url = new URI(uri.getScheme(), uri.getAuthority(), null, null, null).toString();
        Map<String, Object> swaggerConfig = new LinkedHashMap<>();
        List<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrls = new LinkedList<>();
        System.out.println("Services = " + discoveryClient.getServices());
        discoveryClient.getServices().stream().filter(s -> !KUBE_SERVICES.contains(s)).forEach(serviceName ->
                swaggerUrls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(serviceName, url + "/" + serviceName + "/v3/api-docs", "")));
        swaggerConfig.put("urls", swaggerUrls);
        return swaggerConfig;
    }
}
