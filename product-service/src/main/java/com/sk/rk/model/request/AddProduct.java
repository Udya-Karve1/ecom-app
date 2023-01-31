package com.sk.rk.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProduct {
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
