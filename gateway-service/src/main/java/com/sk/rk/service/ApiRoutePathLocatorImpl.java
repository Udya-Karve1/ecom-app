package com.sk.rk.service;

import lombok.AllArgsConstructor;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ApiRoutePathLocatorImpl implements RouteLocator {

    private final GatewayService gatewayService;

    private final RouteLocatorBuilder routeLocatorBuilder;



    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();

        Flux<com.sk.rk.model.Route> fluxRoute = Flux.fromStream(gatewayService.getAllRoute().stream());


        return fluxRoute.map(
                api->routesBuilder.route(api.getId()
                        , predicateSpec -> setPredicateSpec(api, predicateSpec)))
                .flatMap (builder -> routesBuilder.build().getRoutes()
                );
    }

    private Buildable<Route> setPredicateSpec(com.sk.rk.model.Route apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getUri());
        if (!StringUtils.isEmpty("asdf asd")) {
            booleanSpec.and()
                    .method("apiRoute.getMethod()");
        }
        return booleanSpec.uri(apiRoute.getUri());
    }
}
