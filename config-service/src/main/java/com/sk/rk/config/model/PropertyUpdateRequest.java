package com.sk.rk.config.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyUpdateRequest extends PropertyAddRequest {
    private Long id;
}
