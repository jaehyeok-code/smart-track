package com.project.smarttrack.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LocationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LocationServiceApplication.class, args);
  }
}

