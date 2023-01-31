package com.sk.rk.repository;

import com.sk.rk.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByProductIdAndProductName(Long productId, String productName);
}
