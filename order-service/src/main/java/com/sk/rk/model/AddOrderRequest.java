package com.sk.rk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AddOrderRequest {
    private Long customerId;
    private Long productId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
