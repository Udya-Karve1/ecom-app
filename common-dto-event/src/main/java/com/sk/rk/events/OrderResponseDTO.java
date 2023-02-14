package com.sk.rk.events;

import com.sk.rk.events.enums.OrderStatus;
import lombok.Data;


@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private Double amount;
    private OrderStatus status;
}
