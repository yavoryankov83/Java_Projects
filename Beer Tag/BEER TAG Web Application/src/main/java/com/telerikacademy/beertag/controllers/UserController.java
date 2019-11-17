package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path = "users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService, UserDetailsManager userDetailsManager) {
    this.userService = userService;
  }

  @GetMapping
  public String usersView(Model model, Principal principal) {
    model.addAttribute("getAllUsers", userService.getAll());
    User currentUser = userService.getUserByUsername(principal.getName());
    model.addAttribute("currentUser", currentUser);
    return "get-users";
  }

  @GetMapping(path = "{id}")
  public String userView(Model model, @Valid @PathVariable(name = "id") int userId) {
    model.addAttribute("user", userService.getUserById(userId));
    model.addAttribute("getTop3MostRankedBeers", userService.getTop3MostRankedUserBeers(userId));
    return "get-user";
  }

  @GetMapping(path = "create")
  public String createUserView(Model model) {
    model.addAttribute("userToCreate", new User());
    return "create-user";
  }

  @PostMapping(path = "create")
  public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "create-user";
    }
    userService.addUser(user);
    return "redirect:/users";
  }

  @GetMapping(path = "delete/{id}")
  public String deleteUserView(@Valid @PathVariable(name = "id") int userId, Model model) {
    model.addAttribute("userToDelete", userService.getUserById(userId));
    return "delete-user";
  }

  @PostMapping(path = "delete/{id}")
  public String deleteUser(@Valid @PathVariable(name = "id") int userId,
                           @Valid @ModelAttribute User userToDelete,
                           BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "delete-user";
    }
    userService.deleteUser(userId);
    return "redirect:/users";
  }
}
