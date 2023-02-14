package com.sk.rk.controller;

import com.sk.rk.service.InventoryService;
import org.glassfish.hk2.api.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{product-id}")
    public Object getInventoryQuantity(@PathVariable("product-id")Long productId) {
        log.info("Inventory endpoint called ..........");
        return inventoryService.getQuantity(productId);
    }

    @PatchMapping("/quantity-decrease/{product-id}/{quantity}")
    public ResponseEntity decreaseInventory(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    ){
        log.info("inventory decrease called............");
        return inventoryService.decreaseQuantity(productId, quantity);
    }

    @PatchMapping("/quantity-increase/{product-id}/{quantity}")
    public ResponseEntity increaseInventory(
            @PathVariable("product-id") Long productId
            , @PathVariable("quantity") Integer quantity
    ){
        log.info("inventory increase called............");
        return inventoryService.increaseQuantity(productId, quantity);
    }
}
