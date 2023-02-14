package com.sk.rk.events;

import lombok.Data;

@Data
public class OrchestratorRequestDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
    private Double amount;
}
