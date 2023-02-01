package com.sk.rk.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "ApplicationId")
    private Long applicationId;

    @Column(name = "ProfileId")
    private Long profileId;

    @Column(name = "Label")
    private String label;
}
