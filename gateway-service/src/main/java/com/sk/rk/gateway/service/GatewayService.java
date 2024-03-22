package com.sk.rk.gateway.service;

import com.sk.rk.gateway.model.Route;
import com.sk.rk.gateway.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.rk.gateway.repository.GatewaySwaggerRepository;
import com.sk.rk.gateway.model.GatewaySwagger;

import java.util.List;

@Service
public class GatewayService {

    @Autowired
    private GatewaySwaggerRepository swaggerRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<GatewaySwagger> getSwaggerList() {
        //return swaggerRepository.findByActive(Boolean.TRUE);
        return swaggerRepository.findAll();
    }

    public List<Route> getAllRoute() {
        //return  routeRepository.findByActive(Boolean.TRUE);
        return routeRepository.findAll();
    }

}
