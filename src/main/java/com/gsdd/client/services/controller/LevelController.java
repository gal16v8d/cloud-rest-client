package com.gsdd.client.services.controller;

import com.gsdd.client.services.model.Level;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("levels")
public class LevelController extends AbstractBaseController {

  @Autowired
  public LevelController(EurekaClient client, RestTemplate restTemplate) {
    super(client, restTemplate, Level.class);
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
