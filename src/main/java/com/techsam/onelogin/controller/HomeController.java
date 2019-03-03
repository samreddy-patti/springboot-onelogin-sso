package com.techsam.onelogin.controller;

import com.sun.javafx.binding.StringFormatter;
import com.techsam.onelogin.user.User;
import java.security.Principal;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String home(ExpiringUsernameAuthenticationToken userToken) {
    User user = (User) userToken.getPrincipal();
    return StringFormatter.format("Welcome %s", user.getId()).getValue();
  }
}
