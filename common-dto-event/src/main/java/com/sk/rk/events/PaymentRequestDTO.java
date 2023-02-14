package com.sk.rk.events;

import lombok.Data;


@Data
public class PaymentRequestDTO {
    private Long customerId;
    private Long orderId;
    private Double amount;
}
