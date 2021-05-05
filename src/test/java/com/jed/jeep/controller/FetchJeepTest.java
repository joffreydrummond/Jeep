package com.jed.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jed.jeep.controller.support.FetchJeepTestSupport;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
    scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data" + ".sql"
    },
    config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest extends FetchJeepTestSupport {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  void testDb() {
    int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "customers");
    System.out.println("Num: " + numRows);
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
    List<Jeep> actual = res.getBody();
    List<Jeep> expected = buildExpected();
    System.out.println(expected);
    actual.forEach(jeep -> jeep.setModelPK(null));
    assertThat(res.getBody()).isEqualTo(expected);
  }

  @Disabled
  @Test
  void testThatAnErrorMessageIsReturnedWhenAInvalidTrimIsSupplied() {

    // given: a valid model, trim and uri

    JeepModel model = JeepModel.WRANGLER;
    String trim = "Invalid value";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

    // when: a connection is made to uri
    ResponseEntity<Map<String, Object>> res =
        getRestTemplate()
            .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

    // then: a not found status code is returned 404 NOT Found
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    // and: error message is returned
    Map<String, Object> error = res.getBody();

    assertThat(error)
        .containsKey("message")
        .containsEntry("status", HttpStatus.NOT_FOUND.value())
        .containsEntry("uri","/jeeps" )
        .containsKey("timestamp")
        .containsEntry("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
  }
}
