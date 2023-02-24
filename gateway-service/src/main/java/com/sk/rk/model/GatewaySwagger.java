package com.sk.rk.model;


import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "gateway_swagger")
@Data
public class GatewaySwagger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gatewaySwaggerId;
    private String serviceName;
    private String serviceUrl;


}
