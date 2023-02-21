package com.sk.rk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("PRODUCT-SERVICE")
public interface InventoryService {

    @GetMapping("/v1/api/product/inventory/quantity/{product-id}")
    Object getQuantity(@PathVariable("product-id")Long productId);

    @PostMapping("/v1/api/product/quantity-decrease/{product-id}/{quantity}")
    ResponseEntity decreaseQuantity(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    );

    @PostMapping("/v1/api/product/quantity-increase/{product-id}/{quantity}/1")
    ResponseEntity increaseQuantity(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    );
}
