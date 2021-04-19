package com.jed.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jed"})
public class JeepSales {

  public static void main(String[] args) {
   SpringApplication.run(JeepSales.class, args);
  }
}
