package com.sk.rk.config.model;


import lombok.Data;

@Data
public class PropertyAddRequest {
    private String key;
    private String value;
    private Long applicationId;
    private Long profileId;
    private String label;
}
