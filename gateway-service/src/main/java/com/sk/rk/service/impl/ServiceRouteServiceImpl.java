package com.sk.rk.service.impl;

import com.sk.rk.model.AddUpdateServiceRouteRequest;
import com.sk.rk.model.ServiceRoute;
import com.sk.rk.repository.ServiceRouteRepository;
import com.sk.rk.service.GatewayRouteService;
import com.sk.rk.service.ServiceRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceRouteServiceImpl implements ServiceRouteService {
    @Autowired
    private ServiceRouteRepository serviceRouteRepository;

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @Override
    public Flux<ServiceRoute> findApiRoutes() {
        return serviceRouteRepository.findAll();
    }

    @Override
    public Mono<ServiceRoute> findApiRoute(Long id) {
        return findAndValidateApiRoute(id);
    }

    @Override
    public Mono<Void> createApiRoute(AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest) {
        ServiceRoute apiRoute = setNewApiRoute(new ServiceRoute(), createOrUpdateApiRouteRequest);
        return serviceRouteRepository.save(apiRoute)
                .doOnSuccess(obj -> gatewayRouteService.refreshRoutes())
                .then();
    }

    @Override
    public Mono<Void> updateApiRoute(Long id, AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest) {
        return findAndValidateApiRoute(id)
                .map(apiRoute -> setNewApiRoute(apiRoute, createOrUpdateApiRouteRequest))
                .flatMap(serviceRouteRepository::save)
                .doOnSuccess(obj -> gatewayRouteService.refreshRoutes())
                .then();    }

    @Override
    public Mono<Void> deleteApiRoute(Long id) {
        return findAndValidateApiRoute(id)
                .flatMap(apiRoute -> serviceRouteRepository.deleteById(apiRoute.getRouteId()))
                .doOnSuccess(obj -> gatewayRouteService.refreshRoutes());
    }

    private Mono<ServiceRoute> findAndValidateApiRoute(Long id) {
        return serviceRouteRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException(String.format("Api route with id %d not found", id))));
    }

    private ServiceRoute setNewApiRoute(ServiceRoute apiRoute,
                                    AddUpdateServiceRouteRequest createOrUpdateApiRouteRequest) {
        apiRoute.setPath(createOrUpdateApiRouteRequest.getPath());
        apiRoute.setMethod(createOrUpdateApiRouteRequest.getMethod());
        apiRoute.setUri(createOrUpdateApiRouteRequest.getUri());
        return apiRoute;
    }
}
