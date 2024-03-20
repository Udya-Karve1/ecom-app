package com.sk.rk.customer.controller;

import com.sk.rk.controller.CustomerController;
import com.sk.rk.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;


    @Test
    void getApplicationProfileTest() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/any/any")).andExpect(status().isOk());
    }


}
