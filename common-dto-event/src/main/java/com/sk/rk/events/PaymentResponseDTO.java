package com.sk.rk.events;

import com.sk.rk.events.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponseDTO {
    private Long customerId;
    private Long orderId;
    private BigDecimal amount;
    private PaymentStatus status;
}
