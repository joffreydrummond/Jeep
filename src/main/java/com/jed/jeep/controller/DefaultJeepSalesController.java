package com.jed.jeep.controller;

import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import com.jed.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController @Slf4j
public class DefaultJeepSalesController implements JeepSalesController {
  @Autowired
  private JeepSalesService jeepSalesService;

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("model={}, trim={}", model, trim);
    return jeepSalesService.fetchJeeps(model, trim);
  }
}


