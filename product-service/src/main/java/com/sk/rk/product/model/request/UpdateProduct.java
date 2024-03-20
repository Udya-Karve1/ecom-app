package com.sk.rk.product.model.request;

import lombok.Data;

@Data
public class UpdateProduct extends AddProduct {
    private Long productId;
}
