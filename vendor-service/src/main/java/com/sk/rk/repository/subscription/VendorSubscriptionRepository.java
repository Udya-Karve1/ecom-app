package com.sk.rk.repository.subscription;

import com.sk.rk.model.VendorSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorSubscriptionRepository extends JpaRepository<VendorSubscription, Long> {
}
