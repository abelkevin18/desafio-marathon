package com.test.marathon.demo.controller;

import com.test.marathon.demo.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping(value = "/person")
@Slf4j
public class PersonController {

  @GetMapping("/register")
  public String getRequest() {
    return "person/register";
  }

  @GetMapping("/find")
  public String getRuc() {
    return "person/find";
  }
}
