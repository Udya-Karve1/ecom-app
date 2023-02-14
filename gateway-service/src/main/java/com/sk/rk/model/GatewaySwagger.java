package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

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
