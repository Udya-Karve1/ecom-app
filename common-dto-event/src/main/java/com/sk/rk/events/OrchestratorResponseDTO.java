package com.sk.rk.events;

import com.sk.rk.events.enums.OrderStatus;
import lombok.Data;


@Data
public class OrchestratorResponseDTO {
    private Long customerId;
    private Long productId;
    private Long orderId;
    private Double amount;
    private OrderStatus status;

}
