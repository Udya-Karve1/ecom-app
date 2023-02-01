package com.sk.rk.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddUpdateRequest {

    private Long customerOrderId;
    private Long customerId;
    private Long productId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Long orderId;
}
