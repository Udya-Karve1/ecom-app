package com.sk.rk.vendor.repository.subscription;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorSubscriptionRepository extends JpaRepository<VendorSubscription, Long> {
}
