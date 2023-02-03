package com.sk.rk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyUpdateRequest extends PropertyAddRequest {
    private Long Id;
}
