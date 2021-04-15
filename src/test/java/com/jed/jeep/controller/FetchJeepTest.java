package com.jed.jeep.controller;

import com.jed.jeep.controller.support.BaseTest;
import com.jed.jeep.controller.support.FetchJeepTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchJeepTest extends FetchJeepTestSupport {
  @Test
  void name() {}
}
