package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Application")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    Long applicationId;

    @Column(name = "application")
    String application;

    @OneToMany(mappedBy = "application")
    private List<Property> propertyList = new ArrayList<>();
}
