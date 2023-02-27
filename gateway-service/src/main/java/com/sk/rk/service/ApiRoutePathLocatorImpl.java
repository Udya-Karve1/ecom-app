package com.sk.rk.service;

import lombok.AllArgsConstructor;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.util.CollectionUtils;
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


        gatewayService.getAllRoute().stream()
                .map(routeEntity->{
                    return routesBuilder.route(routeEntity.getId(), predicateSpec -> setPredicateSpec(routeEntity, predicateSpec)).build();

                }).flatMap(locator->locator.getRoutes().toStream()).;







        RouteLocator routeLocator = routesBuilder
                .route( "customer", r->r.alwaysTrue()
                        .and()
                        .path("/customer/**")
                        .filters(f->f.rewritePath("/customer/(?<path>.*)", "/$\\{path}"))
                        .uri("lb://CUSTOMER-SERVICE"))
                .route("product", r->r.alwaysTrue()
                        .and()
                        .path("/product/**")
                        .filters(f->f.rewritePath("/product/(?<path>.*)", "/$\\{path}"))
                        .uri("lb://PRODUCT-SERVICE"))
                .route("openapi", r->r.alwaysTrue()
                        .and()
                        .path("/v3/api-docs/**")
                        .filters(f->f.rewritePath("/v3/api-docs/(?<path>.*)", "/$\\{path}/v3/api-docs"))
                        .uri("http://localhost:80")
                )
                .build();

        return routeLocator.getRoutes();


/*        return fluxRoute.map(
                api->routesBuilder.route(api.getId()
                        , predicateSpec -> setPredicateSpec(api, predicateSpec)) )
                .flatMap (builder -> routesBuilder.build().getRoutes()
                );*/
    }

    private Buildable<Route> setPredicateSpec(com.sk.rk.model.Route apiRoute, PredicateSpec predicateSpec) {


        if(!CollectionUtils.isEmpty(apiRoute.getPredicateList())) {
            BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPredicateList().get(0).getPredicateValue());

            if(!CollectionUtils.isEmpty(apiRoute.getFilterList())) {
                String[] filterValue = apiRoute.getFilterList().get(0).getFilterValue().split(",");
                booleanSpec.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(filterValue[0], filterValue[1]));
            }

            return booleanSpec.uri(apiRoute.getUri());
        }

        return predicateSpec.uri(apiRoute.getUri());
    }
}
