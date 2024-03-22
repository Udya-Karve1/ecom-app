package com.sk.rk.gateway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import lombok.Data;

import java.io.Serializable;

@Table(name = "RouteFilter")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RouteFilter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeFilterId;

    private String filterKey;

    private String filterRegex;

    private String filterReplacement;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    private Boolean active;
}
