package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Application")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ApplicationId")
    Long applicationId;

    @Column(name = "Application")
    String application;
}
