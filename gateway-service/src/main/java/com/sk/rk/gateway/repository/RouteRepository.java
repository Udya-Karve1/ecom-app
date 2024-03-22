package com.sk.rk.gateway.repository;

import com.sk.rk.gateway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByActive(@Param("active") Boolean active);
}
