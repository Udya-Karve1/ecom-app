package com.sk.rk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertySource {
    private String name;
    private Map<String, Object> source = new HashMap<>();
}
