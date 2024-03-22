package com.sk.rk.gateway.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "RoutePredicate")
@Data
public class RoutePredicate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoutePredicateId")
    private Long routePredicateId;

    @Column(name = "PredicateKey")
    private String predicateKey;

    @Column(name = "PredicateValue")
    private String predicateValue;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    @Column(name = "IsActive")
    private Boolean active;
}
