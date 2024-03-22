package com.sk.rk.repository.product;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorProductRepository extends JpaRepository<VendorProduct, Long> {

    List<VendorProduct> findByVendorId(@Param("VendorId") Long vendorId);
}
