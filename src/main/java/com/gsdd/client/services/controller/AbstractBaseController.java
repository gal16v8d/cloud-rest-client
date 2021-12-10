package com.gsdd.client.services.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public abstract class AbstractBaseController {

  private final EurekaClient client;
  private final RestTemplate restTemplate;
  private final Class<?> clazz;

  protected AbstractBaseController(EurekaClient client, RestTemplate restTemplate, Class<?> clazz) {
    this.client = client;
    this.restTemplate = restTemplate;
    this.clazz = clazz;
  }

  public abstract String getApplicationName();

  public abstract String getControllerRoute();

  public abstract String getAllMsg();

  public abstract String getFirstMsg();

  public abstract String fallbackGetAll();

  public abstract String fallbackGetFirst();

  public InstanceInfo getClientInstanceInfo() {
    return client.getNextServerFromEureka(getApplicationName(), false);
  }

  @GetMapping("/")
  @HystrixCommand(fallbackMethod = "fallbackGetAll")
  public String getAll() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    return getAllMsg() + restTemplate.getForObject(rootUrl + getControllerRoute(), String.class);
  }

  @GetMapping("/first")
  @HystrixCommand(fallbackMethod = "fallbackGetFirst")
  public String getFirstCourse() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    return getFirstMsg() + restTemplate.getForObject(rootUrl + getControllerRoute() + "/1", clazz);
  }
}
