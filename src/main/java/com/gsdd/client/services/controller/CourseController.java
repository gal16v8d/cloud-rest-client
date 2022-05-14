package com.gsdd.client.services.controller;

import com.gsdd.client.services.model.Course;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("courses")
public class CourseController extends AbstractBaseController {

  @Autowired
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

  @Override
  public String getAllMsg() {
    return "Our courses are: ";
  }

  @Override
  public String getFirstMsg() {
    return "Our first course is: ";
  }

  @Override
  public String fallbackGetAll() {
    return "Can not render our courses in this moment";
  }

  @Override
  public String fallbackGetFirst() {
    return "Can not render our first course in this moment";
  }
}
