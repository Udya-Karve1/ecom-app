package com.sk.rk.customer.model.request;

import lombok.Data;

@Data
public class UpdateCustomer extends AddCustomer{
    private Long customerId;
}
