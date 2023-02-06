package com.sk.rk.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddOrder {
    private Long customerId;
    private Long productId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
