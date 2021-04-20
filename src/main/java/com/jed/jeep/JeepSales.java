package com.jed.jeep;

import com.jed.ComponentScanMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//in case componentscan doesn't work correctly
//@SpringBootApplication(scanBasePackages = {"com.jed"})

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class JeepSales {

  public static void main(String[] args)  {
      System.out.println("=====================I NEED TO SEE WORDS ON THE CONSOLE=====================");
   SpringApplication.run(JeepSales.class, args);

  }
}
