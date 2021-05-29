package com.jed.jeep.service;

import com.jed.jeep.entity.Order;
import com.jed.jeep.entity.OrderRequest;

public interface JeepOrderService {
    Order createOrder(OrderRequest orderRequest);
}
