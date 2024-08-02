package com.sk.rk.gateway.model;
/*

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Route")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String id;

    private String uri;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<RoutePredicate> predicateList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RouteFilter> filterList = new ArrayList<>();

    private Boolean active;


}
*/
