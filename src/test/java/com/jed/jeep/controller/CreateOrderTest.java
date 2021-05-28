package com.jed.jeep.controller;

import com.jed.jeep.controller.support.CreateOrderTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

    String body =
        "{\n"
            + "  \"customer\":\"MORISON_LINA\",\n"
            + "  \"model\":\"WRANGLER\",\n"
            + "  \"trim\":\"Sport Altitude\",\n"
            + "  \"doors\":\"4\",\n"
            + "  \"color\":\"EXT_NACHO\",\n"
            + "  \"engine\":\"2_0_Turbo\",\n"
            + "  \"tire\":\"35_TOYO\",\n"
            + "  \"options\":[\n"
            + "  \"DOOR_QUAD_4\", \n"
            + "  \"EXT_AEV_LIFT\", \n"
            + "  \"EXT_WARN_WINCH\", \n"
            + "  \"EXT_WARN_BUMPER_FRONT\", \n"
            + "  \"EXT_WARN_BUMPER_REAR\", \n"
            + "  \"EXT_ARB_COMPRESSOR\", \n"
            + "]\n"
            + "}\n"
            + "";
  }
}
