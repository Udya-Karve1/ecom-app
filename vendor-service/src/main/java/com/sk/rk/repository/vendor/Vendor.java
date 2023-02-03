package com.sk.rk.repository.vendor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Vendor")
public class Vendor {
    @Id
    private Long vendorId;
    private String vendorName;
    private String email;
    private Boolean status;
    private String mobile;
}
