package com.sk.rk.repository;


import com.sk.rk.model.entity.Customer;
import com.sk.rk.model.request.AddCustomer;
import com.sk.rk.model.request.UpdateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select COUNT(1) from customer", Integer.class);
    }

    @Override
    public int save(AddCustomer customer) {
        return jdbcTemplate.update("insert into customer (firstName, lastName, email, password) values (?, ?, ?, ?)",
                customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword());
    }

    @Override
    public int update(UpdateCustomer customer) {
        return jdbcTemplate.update("update customer set firstname=?, lastname=?, email=?, password=?, balance=? where customerid = ?",
                customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword(), customer.getBalance(), customer.getCustomerId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from customer where customerid=?", id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("select * from customer", (rs, rowNum) -> new Customer(rs.getLong("customerid")
                , rs.getString("firstname")
                , rs.getString("lastname")
                , rs.getString("email")
                , rs.getString("password")
                , rs.getBigDecimal("balance")
        ));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jdbcTemplate.queryForObject("select * from customer where customerid=?", new Object[]{id},
                (rs, row)->Optional.of(
                        new Customer(rs.getLong("customerid")
                                , rs.getString("firstname")
                                , rs.getString("lastname")
                                , rs.getString("email")
                                , rs.getString("password")
                                , rs.getBigDecimal("balance")
                        )
                        )
                );
    }

    @Override
    public Long getMaxId() {
        return jdbcTemplate.queryForObject("select MAX(customerid) from customer", Long.class);
    }

}
