package com.gsdd.client.services.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
@RequestMapping("configs")
public class WelcomeController {

  private final String welcomeMessage;

  public WelcomeController(@Value("${service.welcome.message}") String message) {
    this.welcomeMessage = message;
  }

  @GetMapping("/welcome")
  public String getWelcomeMsg() {
    return welcomeMessage;
  }

}
