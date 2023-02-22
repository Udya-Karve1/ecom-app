package com.sk.rk.repository.product;

import com.sk.rk.repository.CommonPojo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "VendorProduct")
public class VendorProduct extends CommonPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorProductId;
    private Long vendorId;
    private Long productId;
    private Boolean isActive;
}
