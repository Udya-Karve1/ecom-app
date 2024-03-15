package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "route_filter")
@Data
public class RouteFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeFilterId;

    private String filterKey;

    @Column(name = "filter_regex")
    private String filterRegex;

    @Column(name = "filter_replacement")
    private String filterReplacement;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private Boolean active;
}
