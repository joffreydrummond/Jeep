package com.jed.jeep.controller;

import com.jed.jeep.controller.support.CreateOrderTestSupport;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
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

    //when order is sent to
      ResponseEntity<Order> = getRestTemplate().exchange(null, null, null , null)


      //then a 201 status is returned

      //and the returned order is correct

  }
}
