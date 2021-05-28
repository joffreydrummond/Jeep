package com.jed.jeep.dao;

import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;

import java.util.List;

public interface JeepOrderDao {
    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
