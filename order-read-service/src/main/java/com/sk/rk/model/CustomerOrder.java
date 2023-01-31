package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "CustomerOrder")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerOrderId;

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;

    private Long productId;
    private String productName;
    private BigDecimal price;

    private Long orderId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
