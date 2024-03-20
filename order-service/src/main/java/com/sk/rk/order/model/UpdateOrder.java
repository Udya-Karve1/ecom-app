package com.sk.rk.order.model;

import lombok.Data;

@Data
public class UpdateOrder extends AddOrder {
    private Long orderId;
}
