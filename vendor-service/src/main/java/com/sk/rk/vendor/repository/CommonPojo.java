package com.sk.rk.vendor.repository;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommonPojo {
    private Timestamp dateCreated;
    private Timestamp dateModified;
}

