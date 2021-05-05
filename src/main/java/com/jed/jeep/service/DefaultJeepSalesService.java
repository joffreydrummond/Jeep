package com.jed.jeep.service;

import com.jed.jeep.dao.JeepOrderDao;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
  @Autowired private JeepOrderDao jeepOrderDao;

  @Override @Transactional(readOnly = true)
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("The fetchJeeps method was called with model={}, trim={}", model, trim);

    List<Jeep> jeeps = jeepOrderDao.fetchJeeps(model, trim);

    if (jeeps.isEmpty()){
      String msg = String.format("No jeeps found with model=%s and trim=%s", model, trim);

      throw new NoSuchElementException(msg);
    }

    Collections.sort(jeeps);
    return jeeps;
  }
}
