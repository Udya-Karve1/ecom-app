package com.sk.rk.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sk.rk.exception.BaseException;
import com.sk.rk.model.entity.Product;
import com.sk.rk.model.request.AddProduct;
import com.sk.rk.model.request.UpdateProduct;
import com.sk.rk.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(Long productId) throws BaseException {
        return repository.findById(productId).orElseThrow(() -> new BaseException(401, "Product not found."));
    }

    public List<Product> searchProduct(Long productId, String productName) {
        return repository.findByProductIdAndProductName(productId, productName);
    }

    public Product addProduct(AddProduct product) {
        return repository.save(new Product(product.getProductName(), product.getPrice(), product.getQuantity()));
    }

    public Product updateProduct(UpdateProduct product) {
        return repository.save(new Product(product.getProductId(), product.getProductName(), product.getPrice(), product.getQuantity()));
    }

    public void deleteProduct(Long productId) {
        repository.deleteById(productId);
    }

    public Map<String, Object> getQuantity(Long productId) throws BaseException {
        return Collections.singletonMap("available-quantity", getProductById(productId).getQuantity());
    }

    public UpdateProduct decreaseQuantity(Long productId, Integer quantity) throws BaseException {
        Product product = getProductById(productId);
        UpdateProduct updateProduct = new UpdateProduct();
        BeanUtils.copyProperties(product, updateProduct);
        updateProduct.setQuantity(updateProduct.getQuantity()-Math.abs(quantity));

        updateProduct(updateProduct);
        return updateProduct;
    }


    public UpdateProduct increaseQuantity(Long productId, Integer quantity) throws BaseException {
        Product product = getProductById(productId);
        UpdateProduct updateProduct = new UpdateProduct();
        BeanUtils.copyProperties(product, updateProduct);
        updateProduct.setQuantity(updateProduct.getQuantity()+Math.abs(quantity));

        updateProduct(updateProduct);
        return updateProduct;
    }

}
