package com.sk.rk.service;

import com.sk.rk.model.AddOrder;
import com.sk.rk.model.Order;
import com.sk.rk.model.UpdateOrder;

public interface OrderCommandService {
    Order createOrder(AddOrder order);
    Order modifiedOrder(UpdateOrder order);
    void updateOrderStatus(Long orderId, String orderStatus);
}
