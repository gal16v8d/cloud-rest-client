package com.gsdd.client.services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@OpenAPIDefinition(info = @Info(title = "Cloud Client API", version = "2.0",
    description = "REST client using Eureka",
    contact = @Contact(email = "alex.galvis.sistemas@gmail.com")))
public class RestClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestClientApplication.class, args);
  }
}
