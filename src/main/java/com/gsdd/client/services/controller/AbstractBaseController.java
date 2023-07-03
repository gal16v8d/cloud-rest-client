package com.gsdd.client.services.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseController {

  private static final String ALL_MSG = "Our %ss are: %s";
  private static final String ALL_ERROR_MSG = "Can not render our %ss in this moment: %s";
  private static final String FIRST_MSG = "Our first %s is: %s";
  private static final String FIRST_ERROR_MSG = "Can not render our first %s in this moment";

  private final EurekaClient client;
  private final WebClient.Builder restClient;
  private final Class<?> clazz;

  public abstract String getApplicationName();

  public abstract String getControllerRoute();

  public String fallbackGetAll(Exception e) {
    return ALL_ERROR_MSG.formatted(this.clazz.getSimpleName().toLowerCase(), e.getMessage());
  }

  public String fallbackGetFirst() {
    return FIRST_ERROR_MSG.formatted(this.clazz.getSimpleName().toLowerCase());
  }

  public InstanceInfo getClientInstanceInfo() {
    return client.getNextServerFromEureka(getApplicationName(), false);
  }

  @GetMapping
  @CircuitBreaker(name = "getAll", fallbackMethod = "fallbackGetAll")
  public String getAll() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    String result = restClient.baseUrl(rootUrl)
        .build()
        .get()
        .uri(getControllerRoute())
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return ALL_MSG.formatted(this.clazz.getSimpleName().toLowerCase(), result);
  }

  @GetMapping("/first")
  @CircuitBreaker(name = "getFirst", fallbackMethod = "fallbackGetFirst")
  public String getFirst() {
    String rootUrl = getClientInstanceInfo().getHomePageUrl();
    Object result = restClient.baseUrl(rootUrl)
        .build()
        .get()
        .uri(getControllerRoute() + "/1")
        .retrieve()
        .bodyToMono(clazz)
        .block();
    return FIRST_MSG.formatted(this.clazz.getSimpleName().toLowerCase(), result.toString());
  }
}
