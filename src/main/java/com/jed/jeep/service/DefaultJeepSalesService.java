package com.jed.jeep.service;

import com.jed.jeep.entity.Jeep;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService{
    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        log.info("The fetchJeeps method was called with model={}, trim={}", model, trim);
        return null;
    }
}
