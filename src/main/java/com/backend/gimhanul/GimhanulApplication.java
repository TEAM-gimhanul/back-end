package com.backend.gimhanul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GimhanulApplication {

  public static void main(String[] args) {
    SpringApplication.run(GimhanulApplication.class, args);
  }

}
