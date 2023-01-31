package com.sk.rk.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddCustomer {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BigDecimal balance;
}
