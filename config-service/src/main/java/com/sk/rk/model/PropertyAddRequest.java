package com.sk.rk.model;


import lombok.Data;

@Data
public class PropertyAddRequest {
    private String key;
    private String value;
    private Long applicationId;
    private Long profileId;
    private String label;
}
