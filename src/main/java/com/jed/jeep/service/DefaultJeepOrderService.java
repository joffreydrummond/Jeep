package com.jed.jeep.service;

import com.jed.jeep.dao.JeepOrderDao;
import com.jed.jeep.entity.Order;
import com.jed.jeep.entity.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultJeepOrderService implements JeepOrderService{
    @Autowired
    private JeepOrderDao jeepOrderDao;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        return jeepOrderDao.createOrder(orderRequest);
    }
}
