package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "order_table")
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long customerId;
    private Long productId;
    private String orderStatus;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
