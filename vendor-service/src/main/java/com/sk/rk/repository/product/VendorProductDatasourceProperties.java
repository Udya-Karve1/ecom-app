package com.sk.rk.repository.product;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vendor_product.datasource")
@Data
public class VendorProductDatasourceProperties {
    private String url;
    private String username;
    private String password;
}
