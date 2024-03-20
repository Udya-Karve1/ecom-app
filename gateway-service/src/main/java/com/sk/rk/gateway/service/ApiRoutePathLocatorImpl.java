package com.sk.rk.gateway.service;

import com.sk.rk.gateway.model.RouteFilter;
import com.sk.rk.gateway.model.RoutePredicate;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;



@AllArgsConstructor
@Slf4j
public class ApiRoutePathLocatorImpl implements RouteLocator {

    private final GatewayService gatewayService;

    private final RouteLocatorBuilder routeLocatorBuilder;


    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();

        gatewayService.getAllRoute().stream().forEach(apiroute->{
            String path = apiroute.getPredicateList().stream().findFirst().orElse(new RoutePredicate()).getPredicateValue();
            RouteFilter routeFilter = apiroute.getFilterList().stream().findFirst().orElse(new RouteFilter());
            log.debug("path: {}, regex: {}, replacement: {}, uri: {}", path, routeFilter.getFilterRegex(), routeFilter.getFilterReplacement(), apiroute.getUri());
            routesBuilder.route(apiroute.getId(), predicateSpec -> setPredicateSpec(apiroute, predicateSpec));
        });

        return routesBuilder.build().getRoutes();

    }

   private Buildable<Route> setPredicateSpec(com.sk.rk.gateway.model.Route apiRoute, PredicateSpec predicateSpec) {


        if(!CollectionUtils.isEmpty(apiRoute.getPredicateList())) {
            RouteFilter routeFilter = apiRoute.getFilterList().stream().findFirst().orElse(new RouteFilter());

            BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPredicateList().get(0).getPredicateValue());

            if(!CollectionUtils.isEmpty(apiRoute.getFilterList())) {
                booleanSpec.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(routeFilter.getFilterRegex(), routeFilter.getFilterReplacement()));
            }

            return booleanSpec.uri(apiRoute.getUri());
        }

        return predicateSpec.uri(apiRoute.getUri());
    }
}
