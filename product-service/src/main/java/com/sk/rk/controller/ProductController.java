package com.sk.rk.controller;

import com.sk.rk.exception.BaseException;
import com.sk.rk.model.entity.Product;
import com.sk.rk.model.request.AddProduct;
import com.sk.rk.model.request.UpdateProduct;
import com.sk.rk.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{product-id}")
    @Operation(summary = "Get product by ID.")
    public ResponseEntity<Product> getProductById(@PathVariable("product-id") Long productId) throws BaseException {
        return new ResponseEntity<>(service.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/search/all")
    @Operation(summary = "Search products.")
    public ResponseEntity<List<Product>> getProductById(@RequestParam(name = "product-id", required = false) Long productId
            , @RequestParam(name = "product-name", required = false) String productName) {
        return new ResponseEntity<>(service.searchProduct(productId, productName), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary = "Add product.")
    public ResponseEntity<Product> addProduct(@RequestBody AddProduct product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update product.")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProduct product) {
        return new ResponseEntity<>(service.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    @Operation(summary = "Delete product.")
    public ResponseEntity<String> deleteProductById(@PathVariable("product-id") Long productId) {
        service.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @GetMapping("/inventory/quantity/{product-id}")
    @Operation(summary = "Get product quantity.")
    public ResponseEntity<Map<String, Object>> getProductQuantity(
            @PathVariable("product-id") Long productId) throws BaseException {
        return new ResponseEntity<>(service.getQuantity(productId), HttpStatus.OK);
    }


    @PostMapping("/quantity-decrease/{product-id}/{quantity}")
    @Operation(summary = "Get product quantity.")
    public ResponseEntity<UpdateProduct> decreaseQuantity(
            @PathVariable(name = "product-id", required = false) Long productId
            , @PathVariable(name = "quantity", required = false) Integer quantity) throws BaseException {
        return new ResponseEntity<>(service.decreaseQuantity(productId, quantity), HttpStatus.OK);
    }

    @PostMapping("/quantity-increase/{product-id}/{quantity}/{one}")
    @Operation(summary = "Get product quantity.")
    public ResponseEntity<UpdateProduct> increaseQuantity(
            @PathVariable(name = "product-id", required = false) Long productId
            , @PathVariable(name = "quantity", required = false) Integer quantity
            , @PathVariable(name = "one", required = false) Integer one) throws BaseException {
        return new ResponseEntity<>(service.increaseQuantity(productId, quantity), HttpStatus.OK);
    }
}
