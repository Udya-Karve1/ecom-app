package com.sk.rk.events;

import com.sk.rk.events.enums.PaymentStatus;
import lombok.Data;


@Data
public class PaymentResponseDTO {
    private Long customerId;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
}
