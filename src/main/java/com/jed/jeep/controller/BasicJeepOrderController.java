package com.jed.jeep.controller;

import com.jed.jeep.entity.Order;
import com.jed.jeep.entity.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
public class BasicJeepOrderController implements JeepOrderController{
    @Override
    public Order createOrder(OrderRequest orderRequest) {
        log.debug("Order={}", orderRequest);
        return null;
    }
}