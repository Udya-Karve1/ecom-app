package com.sk.rk.config.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "Properties")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Property_Id")
    private Long id;

    @Column(name = "Key_Name")
    private String key;

    @Column(name = "Key_Value")
    private String value;

    @Column(name = "Label")
    private String label;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Application_Id")
    private Application application;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Profile_Id")
    private Profile profile;

}
