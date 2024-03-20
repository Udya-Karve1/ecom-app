package com.sk.rk.gateway.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sk.rk.gateway.model.GatewaySwagger;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.List;

@Repository
public interface GatewaySwaggerRepository extends R2dbcRepository<GatewaySwagger, Long> {
    List<GatewaySwagger> findByActive(@Param("active")Boolean active);
}
