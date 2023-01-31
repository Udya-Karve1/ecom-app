package com.sk.rk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.rk.model.CustomerOrder;
import com.sk.rk.model.CustomerOrderRequest;
import com.sk.rk.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerOrderRepository repository;


    public List<CustomerOrder> searchCustomerOrder(CustomerOrderRequest request) throws JsonProcessingException {
        return repository.searchCustomerOrder(objectMapper.writeValueAsString(request));
    }
}
