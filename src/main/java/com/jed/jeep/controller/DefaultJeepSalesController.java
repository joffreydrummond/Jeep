package com.jed.jeep.controller;

import com.jed.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController @Slf4j
public class DefaultJeepSalesController implements JeepSalesController {
  @Override
  public List<Jeep> fetchJeeps(String model, String trim) {
    log.info("model={}, trim={}", model, trim);
    return null;
  }
}


