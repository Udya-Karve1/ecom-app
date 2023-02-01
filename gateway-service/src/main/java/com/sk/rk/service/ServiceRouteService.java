package com.sk.rk.service;

import com.sk.rk.model.AddUpdateServiceRouteRequest;
import com.sk.rk.model.ServiceRoute;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ServiceRouteService {

    Flux<ServiceRoute> findApiRoutes();

    Mono<ServiceRoute> findApiRoute(Long id);

    Mono<Void> createApiRoute(AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest);

    Mono<Void> updateApiRoute(Long id, AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest);

    Mono<Void> deleteApiRoute(Long id);
}
