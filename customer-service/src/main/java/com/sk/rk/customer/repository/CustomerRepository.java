package com.sk.rk.customer.repository;

import com.sk.rk.customer.model.entity.Customer;
import com.sk.rk.customer.model.request.AddCustomer;
import com.sk.rk.customer.model.request.UpdateCustomer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    int count();

    int save(AddCustomer customer);

    int update(UpdateCustomer customer);

    int deleteById(Long id);

    List<Customer> findAll();


    Optional<Customer> findById(Long id);

    Long getMaxId();
}
