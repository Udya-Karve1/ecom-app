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
    private String filterValue;


    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}
