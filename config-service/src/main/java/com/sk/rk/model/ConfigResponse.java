package com.sk.rk.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigResponse {
    private String name;
    private List<String> profiles = new ArrayList<>();
    private String label;
    private String version;
    private String state;
    private List<PropertySource> propertySources = new ArrayList<>();
}
