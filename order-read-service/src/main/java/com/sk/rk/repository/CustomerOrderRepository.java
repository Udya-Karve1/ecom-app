package com.sk.rk.repository;

import com.sk.rk.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query(value = "CALL search_customer_order(:search_criteria)", nativeQuery = true)
    List<CustomerOrder> searchCustomerOrder(
            @Param("search_criteria") String searchCriteria
    );
}
