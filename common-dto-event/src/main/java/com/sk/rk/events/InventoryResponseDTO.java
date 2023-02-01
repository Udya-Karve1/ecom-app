package com.sk.rk.events;

import com.sk.rk.events.enums.InventoryStatus;
import lombok.Data;

@Data
public class InventoryResponseDTO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private InventoryStatus status;
}
