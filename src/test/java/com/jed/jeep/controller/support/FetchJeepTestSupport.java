package com.jed.jeep.controller.support;

import com.jed.jeep.entity.Jeep;

import java.util.LinkedList;
import java.util.List;

public class FetchJeepTestSupport extends BaseTest {

  protected List<Jeep> buildExpected() {
List<Jeep> list = new LinkedList<>();

Jeep j1 = Jeep.builder().build();

  return list;
  }
}
