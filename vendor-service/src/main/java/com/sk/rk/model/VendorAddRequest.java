package com.sk.rk.model;

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
