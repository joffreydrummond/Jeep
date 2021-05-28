package com.jed.jeep.controller;

import com.jed.jeep.controller.support.CreateOrderTestSupport;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.Assertions.assertThat;
// import static  org.junit.jupiter.api.Assertions.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
    scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data" + ".sql"
    },
    config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {

  @Test
  void testCreateOrderReturnsSuccess201() {

    String body = createOrderBody();
    String uri = getBaseUriForOrders();
    HttpEntity<String> bodyEntity = new HttpEntity<>(body);

    //when order is sent to
      ResponseEntity<?> res = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity , Object.class);


      //then a 201 status is returned
assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
      //and the returned order is correct

  }
}
