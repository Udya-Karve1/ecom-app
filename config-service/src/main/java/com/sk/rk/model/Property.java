package com.sk.rk.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "Properties")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "`Key`")
    private String key;

    @Column(name = "`Value`")
    private String value;

    @Column(name = "Label")
    private String label;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
