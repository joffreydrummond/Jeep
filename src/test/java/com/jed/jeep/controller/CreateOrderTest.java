package com.jed.jeep.controller;

import com.jed.jeep.controller.support.CreateOrderTestSupport;
import com.jed.jeep.entity.JeepModel;
import com.jed.jeep.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
    scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"
    },
    config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {

  //  @Test
  //  void name() {}

  @Test
  void testCreateOrderReturnsSuccess201() {

    String body = createOrderBody();

    String uri = getBaseUriForOrders();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

    // when order is sent to
    ResponseEntity<Order> res =
        getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

    // then a 201 status is returned
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    // and the returned order is correct

    assertThat(res.getBody()).isNotNull();

    Order order = res.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
    assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
    assertThat(order.getOptions()).hasSize(6);


  }
}
