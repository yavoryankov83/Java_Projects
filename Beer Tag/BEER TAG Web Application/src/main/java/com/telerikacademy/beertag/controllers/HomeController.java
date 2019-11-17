package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String getHomePage(Model model) {
    model.addAttribute("user", new User());
    return "index";
  }
}

