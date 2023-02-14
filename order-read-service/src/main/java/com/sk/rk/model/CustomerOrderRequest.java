package com.sk.rk.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerOrderRequest {
    private Long customerOrderId;

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;

    private Long productId;
    private String productName;
    private Double price;

    private Long orderId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
