package com.jed.jeep.service;

import com.jed.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DefaultJeepSalesService implements JeepSalesService{
    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        return null;
    }
}
