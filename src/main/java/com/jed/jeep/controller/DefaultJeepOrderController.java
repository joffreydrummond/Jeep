package com.jed.jeep.controller;

import com.jed.jeep.entity.Order;
import com.jed.jeep.entity.OrderRequest;
//import com.jed.jeep.service.JeepOrderService;
import com.jed.jeep.service.JeepOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
public class DefaultJeepOrderController implements JeepOrderController{

    @Autowired
    private JeepOrderService jeepOrderService;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        log.debug("Order={}", orderRequest);
        log.debug("I am in createOrder() controller");
        return  jeepOrderService.createOrder(orderRequest);
    }
}
