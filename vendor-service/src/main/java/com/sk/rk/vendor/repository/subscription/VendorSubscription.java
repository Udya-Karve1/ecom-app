package com.sk.rk.vendor.repository.subscription;

import com.sk.rk.vendor.repository.CommonPojo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
public class VendorSubscription extends CommonPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorSubscriptionId;
    private Integer duration;
    private Timestamp startDate;
    private Long subscriptionId;
    private Boolean status;
}
