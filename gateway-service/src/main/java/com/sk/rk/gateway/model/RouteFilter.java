package com.sk.rk.gateway.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Table(name = "RouteFilter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteFilter {

    @Id
    private Long routeFilterId;

    private String filterKey;

    private String filterRegex;

    private String filterReplacement;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

    private Boolean active;
}
