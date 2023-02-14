package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "route_predicate")
@Data
public class RoutePredicate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routePredicateId;

    private String predicateKey;
    private String predicateValue;


    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}
