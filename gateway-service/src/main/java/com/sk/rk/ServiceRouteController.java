package com.sk.rk;

import com.sk.rk.model.AddUpdateServiceRouteRequest;
import com.sk.rk.model.ServiceRoute;
import com.sk.rk.model.ServiceRouteResponse;
import com.sk.rk.service.ServiceRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
public class ServiceRouteController {
    @Autowired
    private ServiceRouteService routeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<ServiceRouteResponse>> findApiRoutes() {
        return routeService.findApiRoutes()
                .map(this::convertApiRouteIntoApiRouteResponse)
                .collectList()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceRouteResponse> findApiRoute(@PathVariable Long id) {
        return routeService.findApiRoute(id)
                .map(this::convertApiRouteIntoApiRouteResponse)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> createApiRoute(
            @RequestBody @Validated AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest) {
        return routeService.createApiRoute(createOrUpdateApiRouteRequest)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<?> updateApiRoute(@PathVariable Long id,
                                  @RequestBody @Validated AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest) {
        return routeService.updateApiRoute(id, createOrUpdateApiRouteRequest)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<?> deleteApiRoute(@PathVariable Long id) {
        return routeService.deleteApiRoute(id)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private ServiceRouteResponse convertApiRouteIntoApiRouteResponse(ServiceRoute apiRoute) {
        return ServiceRouteResponse.builder()
                .routeId(apiRoute.getRouteId())
                .path(apiRoute.getPath())
                .method(apiRoute.getMethod())
                .uri(apiRoute.getUri())
                .build();
    }
}
