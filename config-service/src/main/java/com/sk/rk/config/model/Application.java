package com.sk.rk.config.model;

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
    @Column(name = "Application_Id")
    Long applicationId;

    @Column(name = "Application_Name")
    String applicationName;

    @OneToMany(mappedBy = "application")
    private List<Property> propertyList = new ArrayList<>();
}
