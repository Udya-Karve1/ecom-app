package com.sk.rk.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sk.rk.gateway.model.GatewaySwagger;

import java.util.List;

@Repository
public interface GatewaySwaggerRepository extends JpaRepository<GatewaySwagger, Long> {
    List<GatewaySwagger> findByActive(@Param("active")Boolean active);
}
