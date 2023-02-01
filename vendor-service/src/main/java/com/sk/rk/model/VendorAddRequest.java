package com.sk.rk.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendorAddRequest {
    private Long vendorId;
    private Long subscriptionId;
    private Integer duration;
    private BigDecimal subscriptionAmount;
    private String vendorName;
    private String email;
    private String mobile;


}
