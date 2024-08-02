package com.sk.rk.gateway.handler;

import com.sk.rk.gateway.config.GatewayRoutesRefresher;
import com.sk.rk.gateway.model.ApiRoute;
import com.sk.rk.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component

public class ApiRouteHandler {


    @Autowired
    public ApiRouteHandler(RouteService routeService, RouteLocator routeLocator, GatewayRoutesRefresher gatewayRoutesRefresher) {
        this.routeService = routeService;
        this.routeLocator = routeLocator;
        this.gatewayRoutesRefresher = gatewayRoutesRefresher;
    }

    private RouteService routeService;

    private RouteLocator routeLocator;

    private GatewayRoutesRefresher gatewayRoutesRefresher;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<ApiRoute> apiRoute = serverRequest.bodyToMono(ApiRoute.class);
        return apiRoute.flatMap(route ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(routeService.create(route), ApiRoute.class));
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        final String apiId = serverRequest.pathVariable("routeId");
        Mono<ApiRoute> apiRoute = routeService.getById(apiId);
        return apiRoute.flatMap(route -> ServerResponse.ok()
                        .body(fromValue(route)))
                .switchIfEmpty(ServerResponse.notFound()
                        .build());
    }

    public Mono<ServerResponse> refreshRoutes(ServerRequest serverRequest) {
        gatewayRoutesRefresher.refreshRoutes();
        return ServerResponse.ok().body(BodyInserters.fromObject("Routes reloaded successfully"));
    }
}