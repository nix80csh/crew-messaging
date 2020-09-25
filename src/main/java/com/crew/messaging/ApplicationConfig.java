package com.crew.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationConfig extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationConfig.class, args);
  }

}
