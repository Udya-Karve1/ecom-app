package com.sk.rk.product;

import com.sk.rk.common.exception.BaseException;
import com.sk.rk.common.util.CommonUtil;
import com.sk.rk.product.controller.ProductController;
import com.sk.rk.product.model.entity.Product;
import com.sk.rk.product.model.request.AddProduct;
import com.sk.rk.product.model.request.UpdateProduct;
import com.sk.rk.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("wfo")
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getProductByIdTest() throws Exception {
        when(productService.getProductById (anyLong())).thenReturn(new Product());
        this.mockMvc.perform(get("/v1/api/product/1")).andExpect(status().isOk());
    }

    @Test
    public void getProductByIdTestException() throws Exception {
        doThrow(new BaseException(401, "Product not found.")).when(productService).getProductById(anyLong());
        Assertions.assertThrows(BaseException.class, () -> this.productService.getProductById(1L));
    }

    @Test
    public void searchProductsTest() throws Exception {
        when(productService.getProductById (anyLong())).thenReturn(new Product());
        this.mockMvc.perform(get("/v1/api/product/search/all")).andExpect(status().isOk());
    }


    @Test
    public void addProductTest() throws Exception {
        when(productService.addProduct(any())).thenReturn(new Product());
        this.mockMvc.perform(post("/v1/api/product")
                .content(CommonUtil.asJsonString(new AddProduct()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }


    @Test
    public void updateProductTest() throws Exception {
        when(productService.updateProduct(any())).thenReturn(new Product());
        this.mockMvc.perform(put("/v1/api/product")
                .content(CommonUtil.asJsonString(new UpdateProduct()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }


    @Test
    public void deleteProductTest() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());
        this.mockMvc.perform(delete("/v1/api/product/1")).andExpect(status().isOk());
    }


    @Test
    public void deleteProductQuantityTest() throws Exception {
        when(productService.getQuantity(anyLong())).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/v1/api/product/inventory/quantity/1")).andExpect(status().isOk());
    }


    @Test
    public void decreaseQuantityTest() throws Exception {
        when(productService.decreaseQuantity(anyLong(), anyInt())).thenReturn(new UpdateProduct());
        this.mockMvc.perform(patch("/v1/api/product/quantity-decrease/1/1")).andExpect(status().isOk());
    }

    @Test
    public void increaseQuantityTest() throws Exception {
        when(productService.increaseQuantity(anyLong(), anyInt())).thenReturn(new UpdateProduct());
        this.mockMvc.perform(patch("/v1/api/product/quantity-increase/1/1/1")).andExpect(status().isOk());
    }

}
