package com.sk.rk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sk.rk.model.GatewaySwagger;

@Repository
public interface GatewaySwaggerRepository extends JpaRepository<GatewaySwagger, Long> {
}
