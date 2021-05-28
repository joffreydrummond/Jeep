package com.jed.jeep.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
        scripts = {
                "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
                "classpath:flyway/migrations/V1.1__Jeep_Data" + ".sql"
        },
        config = @SqlConfig(encoding = "utf-8"))
public class CreateOrderTest {
}
