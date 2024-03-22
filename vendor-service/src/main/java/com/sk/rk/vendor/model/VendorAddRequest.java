package com.sk.rk.vendor.model;

import lombok.Data;


@Data
public class VendorAddRequest {
    private Long vendorId;
    private Long subscriptionId;
    private Integer duration;
    private Double subscriptionAmount;
    private String vendorName;
    private String email;
    private String mobile;
}