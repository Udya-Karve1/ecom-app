package com.sk.rk.events;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
}
