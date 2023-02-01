package com.sk.rk.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private String status;
    private Long orderId;
    private Long customerId;
    private Long productId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
