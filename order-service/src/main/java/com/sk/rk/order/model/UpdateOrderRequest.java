package com.sk.rk.order.model;

import lombok.Data;

@Data
public class UpdateOrderRequest extends AddOrderRequest {
    private Long orderId;
}
