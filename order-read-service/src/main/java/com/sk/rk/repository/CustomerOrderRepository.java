package com.sk.rk.repository;

import com.sk.rk.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query(value = "CALL search_customer_order(:search_criteria)", nativeQuery = true)
    List<CustomerOrder> searchCustomerOrder(
            @Param("search_criteria") String searchCriteria
    );

    List<CustomerOrder> findByOrderId(Long orderId);

    @Query(value = "Update CustomerOrder set DateUpdated=:dateUpdated, OrderStatus=:orderStatus " +
            "where CustomerOrderId=:customerOrderId", nativeQuery = true)
    void updateOrderStatus(
            @Param("customerOrderId") Long customerOrderId
            , @Param("dateUpdate")Timestamp dateUpdate
            , @Param("orderStatus")String orderStatus
    );
}
