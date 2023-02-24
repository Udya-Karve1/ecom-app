package com.sk.rk.service;

import lombok.AllArgsConstructor;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

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
                        , predicateSpec -> setPredicateSpec(api, predicateSpec)) )
                .flatMap (builder -> routesBuilder.build().getRoutes()
                );
    }

    private Buildable<Route> setPredicateSpec(com.sk.rk.model.Route apiRoute, PredicateSpec predicateSpec) {

        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getUri());


        if (!CollectionUtils.isEmpty (apiRoute.getFilterList())) {

            String[] filterValue = apiRoute.getFilterList().get(0).getFilterValue().split(",");

                    booleanSpec.filters(gatewayFilterSpec -> {return gatewayFilterSpec.rewritePath(filterValue[0], filterValue[1]);});

        }
        return booleanSpec.uri(apiRoute.getUri());
    }
}
