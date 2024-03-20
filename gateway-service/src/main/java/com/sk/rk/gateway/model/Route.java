package com.sk.rk.gateway.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Route")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    private Long routeId;

    private String id;

    private String uri;

    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RoutePredicate> predicateList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RouteFilter> filterList = new ArrayList<>();

    private Boolean active;
}
