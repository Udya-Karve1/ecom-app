package com.sk.rk.model.request;

import lombok.Data;

@Data
public class AddProduct {
    private String productName;
    private Double price;
    private Integer quantity;
}
