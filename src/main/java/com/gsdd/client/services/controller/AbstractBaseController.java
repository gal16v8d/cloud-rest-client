package com.gsdd.client.services.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseController {

  private final EurekaClient client;
  private final RestTemplate restTemplate;
  private final Class<?> clazz;

  public abstract String getApplicationName();

  public abstract String getControllerRoute();

  public String getAllMsg() {
    return "Our %ss are: ".formatted(this.clazz.getSimpleName().toLowerCase());
  }

  public String getFirstMsg() {
    return "Our first %s is: ".formatted(this.clazz.getSimpleName().toLowerCase());
  }

  public String fallbackGetAll(Exception e) {
    return "Can not render our %ss in this moment: %s"
        .formatted(this.clazz.getSimpleName().toLowerCase(), e.getMessage());
  }

  public String fallbackGetFirst() {
    return "Can not render our first %s in this moment"
        .formatted(this.clazz.getSimpleName().toLowerCase());
  }

  public InstanceInfo getClientInstanceInfo() {
    return client.getNextServerFromEureka(getApplicationName(), false);
  }

  @GetMapping
  @CircuitBreaker(name = "getAll", fallbackMethod = "fallbackGetAll")
  public String getAll() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    return getAllMsg() + restTemplate.getForObject(rootUrl + getControllerRoute(), String.class);
  }

  @GetMapping("/first")
  @CircuitBreaker(name = "getFirst", fallbackMethod = "fallbackGetFirst")
  public String getFirst() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    return getFirstMsg() + restTemplate.getForObject(rootUrl + getControllerRoute() + "/1", clazz);
  }
}
