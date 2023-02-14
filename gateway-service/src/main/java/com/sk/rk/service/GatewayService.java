package com.sk.rk.service;

import com.netflix.discovery.converters.Auto;
import com.sk.rk.model.Route;
import com.sk.rk.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.rk.repository.GatewaySwaggerRepository;
import com.sk.rk.model.GatewaySwagger;

import java.util.List;

@Service
public class GatewayService {

    @Autowired
    private GatewaySwaggerRepository swaggerRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<GatewaySwagger> getSwaggerList() {
        return swaggerRepository.findAll();
    }

    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

}
