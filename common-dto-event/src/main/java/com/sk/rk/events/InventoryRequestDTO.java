package com.sk.rk.events;

import lombok.Data;

@Data
public class InventoryRequestDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
}
