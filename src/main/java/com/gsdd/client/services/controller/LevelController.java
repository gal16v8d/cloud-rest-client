package com.gsdd.client.services.controller;

import com.gsdd.client.services.model.Level;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("levels")
public class LevelController extends AbstractBaseController {

  public LevelController(EurekaClient client, WebClient.Builder levelClient) {
    super(client, levelClient, Level.class);
  }

  @Override
  public String getApplicationName() {
    return "dw2-svc";
  }

  @Override
  public String getControllerRoute() {
    return "/v1/levels";
  }
}
