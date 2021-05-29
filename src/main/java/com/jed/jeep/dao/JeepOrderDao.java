package com.jed.jeep.dao;

import com.jed.jeep.entity.*;

import java.util.List;

public interface JeepOrderDao {
    List<Jeep> fetchJeeps(JeepModel model, String trim);

    Customer fetchCustomer(String customer);

//    Order createOrder(OrderRequest orderRequest);
}
