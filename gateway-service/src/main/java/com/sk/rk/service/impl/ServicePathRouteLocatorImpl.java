package com.sk.rk.service.impl;

import com.sk.rk.model.ServiceRoute;
import com.sk.rk.service.ServiceRouteService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ServicePathRouteLocatorImpl implements RouteLocator {
    private final ServiceRouteService apiRouteService;

    private final RouteLocatorBuilder routeLocatorBuilder;

    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();

        return apiRouteService.findApiRoutes()
                .map(apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getRouteId()),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)))
                .collectList()
                .flatMapMany(builders -> routesBuilder.build()
                        .getRoutes());
    }

    private Buildable<Route> setPredicateSpec(ServiceRoute apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
        if (!StringUtils.isEmpty(apiRoute.getMethod())) {
            booleanSpec.and()
                    .method(apiRoute.getMethod());
        }
        return booleanSpec.uri(apiRoute.getUri());
    }
}
