package com.jed.jeep.controller;

import com.jed.jeep.entity.Jeep;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultJavaSalesController implements JeepSalesController{
    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        return null;
    }
}
