package com.sk.rk.service;

import com.sk.rk.repository.vendor.Vendor;
import com.sk.rk.model.VendorAddRequest;
import com.sk.rk.repository.subscription.VendorSubscription;
import com.sk.rk.repository.subscription.VendorSubscriptionRepository;
import com.sk.rk.repository.vendor.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorSubscriptionRepository subscriptionRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void addVendor(VendorAddRequest vendorAddRequest) {
        vendorRepository.save(createEntityVendor(vendorAddRequest));
        subscriptionRepository.save(createEntityVendorSubscription(vendorAddRequest));
    }

    private VendorSubscription createEntityVendorSubscription(VendorAddRequest request) {
        VendorSubscription subscription = new VendorSubscription();
        subscription.setSubscriptionId(request.getSubscriptionId());
        subscription.setDuration(request.getDuration());
        subscription.setStatus(Boolean.TRUE);
        subscription.setStartDate(new Timestamp(System.currentTimeMillis()));

        return subscription;
    }

    private Vendor createEntityVendor(VendorAddRequest request) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(request.getVendorName());
        vendor.setMobile(request.getMobile());
        vendor.setEmail(request.getEmail());
        vendor.setStatus(Boolean.TRUE);

        return vendor;
    }
}
