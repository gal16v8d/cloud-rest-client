package com.gsdd.client.services.controller;

import com.gsdd.client.services.model.Course;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("courses")
public class CourseController extends AbstractBaseController {

  public CourseController(EurekaClient client, WebClient.Builder courseClient) {
    super(client, courseClient, Course.class);
  }

  @Override
  public String getApplicationName() {
    return "fx-course-app";
  }

  @Override
  public String getControllerRoute() {
    return "/courses";
  }
}
