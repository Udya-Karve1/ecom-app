package com.sk.rk.product;

import com.sk.rk.common.exception.BaseException;
import com.sk.rk.product.model.entity.Product;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getProductByIdTest() throws BaseException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        Assertions.assertNotNull(this.productService.getProductById(1L));
    }

    @Test
    public void getProductByIdTestException() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(BaseException.class, ()->this.productService.getProductById(1L));
    }

}
