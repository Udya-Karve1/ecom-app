package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    private String id;
    private String uri;

    @OneToMany(mappedBy = "route")
    private List<RoutePredicate> predicateList = new ArrayList<>();
}
