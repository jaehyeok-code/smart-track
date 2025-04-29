package com.project.smarttrack.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DispatchServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DispatchServiceApplication.class, args);
  }
}
