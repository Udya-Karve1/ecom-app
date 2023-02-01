package com.sk.rk.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@Table(name = "ServiceRoute")
@NoArgsConstructor
public class ServiceRoute {
    @Id
    private Long routeId;
    private String path;
    private String method;
    private String uri;
}
