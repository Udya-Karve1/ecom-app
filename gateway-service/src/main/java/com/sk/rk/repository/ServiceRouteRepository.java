package com.sk.rk.repository;

import com.sk.rk.model.ServiceRoute;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRouteRepository extends ReactiveCrudRepository<ServiceRoute, Long> {
}
