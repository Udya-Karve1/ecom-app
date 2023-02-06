package com.sk.rk.repository;

import com.sk.rk.model.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "CALL udp_search_product(:ProductId, :ProductName)", nativeQuery = true)
    List<Map<String, Object>> findProduct(@Param("ProductId") Long productId, @Param("ProductName") String productName);
}
