package com.sk.rk.model.request;

import lombok.Data;

@Data
public class UpdateProduct extends AddProduct {
    private Long productId;
}
