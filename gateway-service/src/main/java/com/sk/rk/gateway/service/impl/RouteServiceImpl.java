package com.sk.rk.gateway.service.impl;

import com.sk.rk.gateway.model.ApiRoute;
import com.sk.rk.gateway.repository.RouteRepository;
import com.sk.rk.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;
/*
    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
*/

    @Override
    public Flux<ApiRoute> getAll() {
        return this.routeRepository.findAll();
    }

    @Override
    public Mono<ApiRoute> create(ApiRoute apiRoute) {
        Mono<ApiRoute> route = this.routeRepository.save(apiRoute);
        return route;
    }

    @Override
    public Mono<ApiRoute> getById(String id) {
        return this.routeRepository.findById(id);
    }
}