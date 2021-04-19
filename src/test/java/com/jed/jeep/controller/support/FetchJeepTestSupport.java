package com.jed.jeep.controller.support;

import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class FetchJeepTestSupport extends BaseTest {

  protected List<Jeep> buildExpected() {
    List<Jeep> list = new LinkedList<>();

    list.add(
        Jeep.builder()
            .modelId(JeepModel.WRANGLER)
            .trimLevel("Sport")
            .numDoors(2)
            .wheelSize(17)
            .basePrice(new BigDecimal("28475.00"))
            .build());

    list.add(
            Jeep.builder()
                    .modelId(JeepModel.WRANGLER)
                    .trimLevel("Sport")
                    .numDoors(4)
                    .wheelSize(17)
                    .basePrice(new BigDecimal("31975.00"))
                    .build());

    return list;
  }
}
