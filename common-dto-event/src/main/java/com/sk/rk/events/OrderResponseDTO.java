package com.sk.rk.events;

import com.sk.rk.events.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private BigDecimal amount;
    private OrderStatus status;
}
