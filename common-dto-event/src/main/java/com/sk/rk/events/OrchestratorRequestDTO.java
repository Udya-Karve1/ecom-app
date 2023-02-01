package com.sk.rk.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrchestratorRequestDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
    private BigDecimal amount;
}
