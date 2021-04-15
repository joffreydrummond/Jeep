package com.jed.jeep.controller;

import com.jed.jeep.controller.support.FetchJeepTestSupport;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchJeepTest extends FetchJeepTestSupport {

  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {

    // Given a valid model, trim, and URI

    JeepModel model = JeepModel.MODEL_ID;

    JeepModel trim = JeepModel.TRIM_LEVEL;
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

        System.out.println(uri);

    // When a connection is made to the URI
        ResponseEntity<Jeep> res = getRestTemplate().getForEntity(uri, Jeep.class);

//    ResponseEntity<Jeep> res =
//        getRestTemplate()
//            .getForEntity(uri, Jeep.class, model, trim, new ParameterizedTypeReference<>() {});

    // Then a valid response code is returned 200 - OK

    assert (res.getStatusCode().equals(HttpStatus.OK));

    System.out.println(getBaseUri());
  }
}
