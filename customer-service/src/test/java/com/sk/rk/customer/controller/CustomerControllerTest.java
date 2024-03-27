package com.sk.rk.customer.controller;

import com.sk.rk.customer.model.entity.Customer;
import com.sk.rk.customer.model.request.AddCustomer;
import com.sk.rk.customer.model.request.UpdateCustomer;
import com.sk.rk.customer.service.CustomerService;
import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.common.exception.BaseException;
import com.sk.rk.common.util.CommonUtil;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Inject
    private CustomerController customerController;


    @Test
    void getCustomerByIdTest() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(new Customer());
        this.mockMvc.perform(get("/v1/api/customer/1")).andExpect(status().isOk());
    }


    @Test
    void getCustomerByIdExceptionTest() throws Exception {
        doThrow(new BaseException(400, "Customer not found")).when(customerService).getCustomerById(anyLong());
        Assertions.assertThrows(BaseException.class, ()->this.customerController.getCustomerById(1L));

    }

    @Test
    void getBalanceTest() throws Exception {
        when(customerService.getBalance(anyLong())).thenReturn(new HashMap<>());
        this.mockMvc.perform(get("/v1/api/customer/balance/1")).andExpect(status().isOk());
    }

    @Test
    void saveCustomerTest() throws Exception {
        when(customerService.addCustomer(any())).thenReturn(new Customer());
        this.mockMvc.perform(post("/v1/api/customer")
                .content(CommonUtil.asJsonString(new AddCustomer()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }


    @Test
    void saveCustomerExceptionTest() throws Exception {
        doThrow(new BaseException(400, "Error while adding customer.")).when(customerService).addCustomer(any());
        Assertions.assertThrows(BaseException.class, () -> this.customerController.saveCustomer(new AddCustomer()));
    }


    @Test
    void updateCustomerTest() throws Exception {
        when(customerService.updateCustomer(any())).thenReturn(new Customer());
        this.mockMvc.perform(put("/v1/api/customer")
                .content(CommonUtil.asJsonString(new AddCustomer()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void updateCustomerExceptionTest() throws Exception {
        doThrow(new BaseException(400, "Error while adding customer.")).when(customerService).updateCustomer(any());
        Assertions.assertThrows(BaseException.class, () -> this.customerController.updateCustomer(new UpdateCustomer()));
    }


    @Test
    void deleteCustomerTest() throws Exception {
        doNothing().when(customerService).deleteCustomer(anyLong());
        this.mockMvc.perform(delete("/v1/api/customer/1")).andExpect(status().isOk());
    }

    @Test
    void getBalanceExceptionTest() throws Exception {
        doThrow(new BaseException(400, "Error accessing customer account detail.")).when(customerService).getBalance(anyLong());
        Assertions.assertThrows(BaseException.class, ()->this.customerController.getBalance(1L));
    }

    @Test
    void debitBalanceTest() throws Exception {
        when(customerService.debitBalance(any())).thenReturn(new HashMap<>());
        this.mockMvc.perform(post("/v1/api/customer/balance/debit")
                .content(CommonUtil.asJsonString(new PaymentRequestDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk());
    }

    @Test
    void debitBalanceExceptionTest() throws Exception {
        doThrow(new BaseException(400, "error while debit balance")).when(customerService).debitBalance(any());
        Assertions.assertThrows(BaseException.class, ()->this.customerController.debitBalance(new PaymentRequestDTO()));
    }

    @Test
    void creditBalanceTest() throws Exception {
        when(customerService.creditBalance(any())).thenReturn(new HashMap<>());
        this.mockMvc.perform(post("/v1/api/customer/balance/credit")
                .content(CommonUtil.asJsonString(new PaymentRequestDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk());
    }


}
