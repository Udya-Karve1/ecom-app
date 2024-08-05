package com.sk.rk.inventory.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient("PRODUCT-SERVICE")
public interface InventoryService {

    @GetMapping("/product/v1/api/inventory/quantity/{product-id}")
    Object getQuantity(@PathVariable("product-id")Long productId);

    @PostMapping("/product/v1/api/quantity-decrease/{product-id}/{quantity}")
    ResponseEntity<Map<String, Object>> decreaseQuantity(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    );

    @PostMapping("/product/v1/api/quantity-increase/{product-id}/{quantity}/1")
    ResponseEntity<Map<String, Object>> increaseQuantity(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    );
}
