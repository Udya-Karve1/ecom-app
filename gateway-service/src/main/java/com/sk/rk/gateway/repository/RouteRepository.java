package com.sk.rk.gateway.repository;


import com.sk.rk.gateway.model.ApiRoute;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends ReactiveCrudRepository<ApiRoute, String> {
}