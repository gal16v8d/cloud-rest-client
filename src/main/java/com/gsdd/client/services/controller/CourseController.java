package com.gsdd.client.services.controller;

import com.gsdd.client.services.model.Course;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("courses")
public class CourseController extends AbstractBaseController {

  public CourseController(EurekaClient client, RestTemplate restTemplate) {
    super(client, restTemplate, Course.class);
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
