package com.sk.rk.repository.subscription;

import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
public class VendorSubscription {
    private Long vendorSubscriptionId;
    private Integer duration;
    private Timestamp startDate;
    private Long subscriptionId;
    private Boolean status;

}
