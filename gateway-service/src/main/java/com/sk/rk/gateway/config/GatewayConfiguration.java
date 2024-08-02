package com.sk.rk.gateway.config;


import com.netflix.discovery.converters.Auto;
import com.sk.rk.gateway.filter.RequestAndResponseLogGlobalFilter;
import com.sk.rk.gateway.handler.ApiRouteHandler;
import com.sk.rk.gateway.service.RouteService;
import com.sk.rk.gateway.service.impl.ApiRouteLocatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class GatewayConfiguration {
    @Autowired
    private RequestAndResponseLogGlobalFilter requestAndResponseLogGlobalFilter;

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        return routeLocatorBuilder.routes().route("order-service",
//                        route -> route.path("/orders/**")
//                                .filters(filter -> {
//                                    filter.addResponseHeader("res-header", "res-header-value");
//                                    return filter;
//                                })
//                                .uri("http://localhost:8081"))
//                .build();
//    }

    @Bean
    public RouteLocator routeLocator(RouteService routeService, RouteLocatorBuilder routeLocationBuilder) {
       /* return routeLocationBuilder.routes()
                .route(r -> r.path("/product-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://PRODUCT-SERVICE"))
                .route(r -> r.path("/customer-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://CUSTOMER-SERVICE"))
                .build();*/

        return new ApiRouteLocatorImpl(routeLocationBuilder, routeService);

    }

    public RouterFunction<ServerResponse> routes(ApiRouteHandler apiRouteHandler) {

        return RouterFunctions.route(POST("/routes")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::create)
                .andRoute(GET("/routes/:routeId")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::getById)
                .andRoute(GET("/routes/refresh-routes")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::refreshRoutes);

    }
}