package com.sk.rk.controller;


import com.sk.rk.exception.BaseException;
import com.sk.rk.model.entity.Product;
import com.sk.rk.service.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Inject
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getProductByIdTest() throws Exception {
        when(this.productService.getProductById(anyLong())).thenReturn(new Product());
        this.mockMvc.perform(get("/v1/api/product/1")).andExpect(status().isOk());
    }

    @Test
    void getProductByIdTestException() throws Exception {
        doThrow(new BaseException()).when(productService).getProductById(anyLong());
        productController.getProductById(1)
        this.mockMvc.perform(get("/v1/api/product/1")).andExpect(status().isOk());
    }
}
