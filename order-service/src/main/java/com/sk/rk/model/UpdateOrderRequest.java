package com.sk.rk.model;

import lombok.Data;

@Data
public class UpdateOrderRequest extends AddOrderRequest {
    private Long orderId;
}
