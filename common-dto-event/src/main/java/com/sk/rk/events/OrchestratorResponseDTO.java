package com.sk.rk.events;

import com.sk.rk.events.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrchestratorResponseDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
    private BigDecimal amount;
    private OrderStatus status;

}
