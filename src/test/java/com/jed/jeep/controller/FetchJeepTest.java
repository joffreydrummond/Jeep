package com.jed.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.jed.jeep.Constants;
import com.jed.jeep.service.JeepSalesService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import java.util.stream.Stream;

class FetchJeepTest extends FetchJeepTestSupport {

  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data" + ".sql"
      },
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatDoNotPolluteTheApplicationContext extends FetchJeepTestSupport {
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

    @Test
    void testThatAnErrorMessageIsReturnedWhenAnUnknownTrimIsSupplied() {

      // given: a valid model, trim and uri

      JeepModel model = JeepModel.WRANGLER;
      String trim = "Unknown value";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

      // when: a connection is made to uri
      ResponseEntity<Map<String, Object>> res =
          getRestTemplate()
              .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

      // then: a not found status code is returned 404 NOT Found
      assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

      // and: error message is returned
      Map<String, Object> error = res.getBody();

      assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @MethodSource("com.jed.jeep.controller.FetchJeepTest#parametersForInvalidInput")
    void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(
        String model, String trim, String reason) {

      // given: a valid model, trim and uri

      String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

      // when: a connection is made to uri
      ResponseEntity<Map<String, Object>> res =
          getRestTemplate()
              .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

      // then: a bad request is returned
      assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

      // and: error message is returned
      Map<String, Object> error = res.getBody();

      assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
    }
  }

  static Stream<Arguments> parametersForInvalidInput() {
    return Stream.of(
            arguments("WRANGLER", "$%^&*&^(", "Trim contains non-alphanumeric characters"),
            arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH + 1), "Trim length too long"),
            arguments("INVALID", "Sport", "Model is not enum value"));
  }
  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data" + ".sql"
      },
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport {
    @MockBean
    private JeepSalesService jeepSalesService;
    @Test
    void testThatAnUnplannedErrorResultsInA500Status() {

      // given: a valid model, trim and uri

      JeepModel model = JeepModel.WRANGLER;
      String trim = "Invalid";
      String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);


      doThrow(new RuntimeException("This hurts my soul...")).when(jeepSalesService).fetchJeeps(model, trim);
      // when: a connection is made to uri
      ResponseEntity<Map<String, Object>> res =
              getRestTemplate()
                      .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
      System.out.println(res.getBody());
      // then: internal server error returned
      assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

      // and: error message is returned
      Map<String, Object> error = res.getBody();

      assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Autowired private JdbcTemplate jdbcTemplate;

//  @Test
//  void testDb() {
//    int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "customers");
//    System.out.println("Num: " + numRows);
//  }
}
