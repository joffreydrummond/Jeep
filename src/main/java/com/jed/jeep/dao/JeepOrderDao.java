package com.jed.jeep.dao;

import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import com.jed.jeep.entity.Order;
import com.jed.jeep.entity.OrderRequest;

import java.util.List;

public interface JeepOrderDao {
    List<Jeep> fetchJeeps(JeepModel model, String trim);

    Order createOrder(OrderRequest orderRequest);
}
