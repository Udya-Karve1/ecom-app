package com.sk.rk.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BigDecimal balance;
}
