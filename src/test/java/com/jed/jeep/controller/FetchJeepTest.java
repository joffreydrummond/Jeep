package com.jed.jeep.controller;

import com.jed.jeep.controller.support.BaseTest;
import com.jed.jeep.controller.support.FetchJeepTestSupport;
import com.jed.jeep.entities.JeepModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchJeepTest extends FetchJeepTestSupport {

  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {


    //Given a valid model, trim, and URI
    JeepModel model = JeepModel.WRANGLER;

    //When a connection is made to the URI

    //Then a valid response code is returned 200 - OK

    System.out.println(getBaseUri());
  }
}
