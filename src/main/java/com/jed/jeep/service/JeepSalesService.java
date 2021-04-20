package com.jed.jeep.service;

import com.jed.jeep.entity.*;

import java.util.List;


public interface JeepSalesService {

    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
