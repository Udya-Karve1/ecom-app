package com.sk.rk.controller;

import com.sk.rk.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{product-id}")
    public Object getInventoryQuantity(@PathVariable("product-id")Long productId) {
        return inventoryService.getQuantity(productId);
    }

    @PatchMapping("/quantity-decrease/{product-id}/{quantity}")
    public ResponseEntity decreaseInventory(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    ){
        return inventoryService.decreaseQuantity(productId, quantity);
    }

    @PatchMapping("/quantity-increase/{product-id}/{quantity}")
    public ResponseEntity increaseInventory(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    ){
        return inventoryService.increaseQuantity(productId, quantity);
    }
}
