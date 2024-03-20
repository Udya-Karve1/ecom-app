package com.sk.rk.gateway.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import lombok.NoArgsConstructor;

@Table(name = "GatewaySwagger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewaySwagger {

    @Id
    private Long gatewaySwaggerId;

    private String serviceName;

    private String serviceUrl;

    private Boolean active;
}
