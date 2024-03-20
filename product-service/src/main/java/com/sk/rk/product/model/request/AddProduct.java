package com.sk.rk.product.model.request;

import lombok.Data;

@Data
public class AddProduct {
    private String productName;
    private Double price;
    private Integer quantity;
}
