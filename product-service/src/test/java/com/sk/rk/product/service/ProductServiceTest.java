package com.sk.rk.product.service;

import com.sk.rk.common.exception.BaseException;
import com.sk.rk.product.model.entity.Product;
import com.sk.rk.product.model.request.AddProduct;
import com.sk.rk.product.model.request.UpdateProduct;
import com.sk.rk.product.repository.ProductRepository;
import com.sk.rk.product.service.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductByIdTest() throws BaseException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        Assertions.assertNotNull(this.productService.getProductById(1L));
    }

    @Test
    void getProductByIdTestException() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(BaseException.class, ()->this.productService.getProductById(1L));
    }


    @Test
    void searchProductTest() {

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("product_id", Long.valueOf("1"));
        responseMap.put("product_name", "test product name");
        responseMap.put("price", Double.valueOf("100"));
        responseMap.put("quantity", Integer.valueOf("100"));

        when(repository.findProduct(anyLong(), anyString())).thenReturn(Collections.singletonList(responseMap));
        Assertions.assertNotNull(this.productService.searchProduct(1l, "test"));
    }

    @Test
    void addProductTest() {
        when(repository.save(any())).thenReturn(new Product());
        Assertions.assertNotNull(this.productService.addProduct(new AddProduct()));
    }

    @Test
    void updateProductTest() {
        when(repository.save(any())).thenReturn(new Product());
        Assertions.assertNotNull(this.productService.updateProduct(new UpdateProduct()));
    }

    @Test
    void getQuantityTest() throws BaseException {
        Product product = new Product();
        product.setQuantity(100);
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        Assertions.assertNotNull(this.productService.getQuantity(1L));
    }

    @Test
    void decreaseQuantityTest() throws BaseException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(prepareProduct()));
        when(repository.save(any())).thenReturn(new Product());

        Assertions.assertNotNull(this.productService.decreaseQuantity(1L, 10));
    }


    @Test
    void increaseQuantityTest() throws BaseException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(prepareProduct()));
        when(repository.save(any())).thenReturn(new Product());

        Assertions.assertNotNull(this.productService.increaseQuantity(1L, 10));
    }


    @Test
    void deleteProductTest() {
        doNothing().when(this.repository).deleteById(anyLong());
        Assertions.assertDoesNotThrow (()->this.productService.deleteProduct(1L));
    }

    private Product prepareProduct() {
        Product product = new Product();
        product.setQuantity(100);

        return product;
    }

    private UpdateProduct prepareUpdateProduct() {
        UpdateProduct product = new UpdateProduct();
        product.setQuantity(100);
        product.setProductId(1L);
        product.setQuantity(100);
        product.setPrice(Double.valueOf("100"));

        return product;
    }

}
