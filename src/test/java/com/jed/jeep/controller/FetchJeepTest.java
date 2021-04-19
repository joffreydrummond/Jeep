package com.jed.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jed.jeep.controller.support.FetchJeepTestSupport;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", "classpath:flyway/migrations/V1.1__Jeep_Data" +
        ".sql"}, config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest extends FetchJeepTestSupport {

  @Test
  void testDb(){

  }

  @Disabled
  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {

    // given: a valid model, trim and uri

    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

    // when: a connection is made to uri
    ResponseEntity<List<Jeep>> res =
        getRestTemplate()
            .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

    // then: a valid status code is returned OK 200
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    // and: the actual list is the same as the expected list
    List<Jeep> expected = buildExpected();
    System.out.println(expected);
    assertThat(res.getBody()).isEqualTo(expected);
  }
}
