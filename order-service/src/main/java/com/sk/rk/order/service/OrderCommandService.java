package com.sk.rk.order.service;

import com.sk.rk.order.model.AddOrder;
import com.sk.rk.order.model.Order;
import com.sk.rk.order.model.UpdateOrder;

public interface OrderCommandService {
    Order createOrder(AddOrder order);
    Order modifiedOrder(UpdateOrder order);
    void updateOrderStatus(Long orderId, String orderStatus);
}
