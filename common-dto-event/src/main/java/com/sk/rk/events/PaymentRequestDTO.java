package com.sk.rk.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private Long customerId;
    private Long orderId;
    private BigDecimal amount;
}
