package com.jed.jeep.service;

import com.jed.jeep.dao.JeepOrderDao;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
  @Autowired private JeepOrderDao jeepOrderDao;

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("The fetchJeeps method was called with model={}, trim={}", model, trim);

    List<Jeep> jeeps = jeepOrderDao.fetchJeeps(model, trim);

    Collections.sort(jeeps);
    return jeeps;
  }
}
