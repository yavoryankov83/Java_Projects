package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.EntityEmailExistException;
import com.telerikacademy.beertag.exceptions.EntityPasswordNotMatchException;
import com.telerikacademy.beertag.exceptions.EntityUserExistException;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
  private static final String USER_EXIST_EXCEPTION =
          "User already exists.";
  private static final String PASSWORD_NOT_MATCH_EXCEPTION =
          "Password didn't match to field password confirmation.";
  private static final String EMAIL_EXIST_EXCEPTION =
          "Email already exists.";

  private UserDetailsManager userDetailsManager;
  private PasswordEncoder passwordEncoder;
  private UserService userService;

  @Autowired
  public RegistrationController(UserDetailsManager userDetailsManager,
                                PasswordEncoder passwordEncoder,
                                UserService userService) {
    this.userDetailsManager = userDetailsManager;
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  @PostMapping("register")
  public String registerUser(@Valid @ModelAttribute User user) {
    if (userDetailsManager.userExists(user.getUsername())) {
      throw new EntityUserExistException(USER_EXIST_EXCEPTION);
    }
    if (!user.getPassword().equals(user.getPasswordConfirmation())) {
      throw new EntityPasswordNotMatchException(PASSWORD_NOT_MATCH_EXCEPTION);
    }
    if (userService.isUserEmailExist(user.getEmail())) {
      throw new EntityEmailExistException(EMAIL_EXIST_EXCEPTION);
    }

    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    org.springframework.security.core.userdetails.User newUser =
            new org.springframework.security.core.userdetails.User(
                    user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities);
    userDetailsManager.createUser(newUser);

    User userByUsername = userService.getUserByUsername(user.getUsername());
    userService.updateUser(userByUsername.getId(), user);
    return "register-confirmation";
  }

  @GetMapping("update/{id}")
  public String showUpdateUserPage(@Valid @PathVariable(name = "id") Integer userId, Model model) {
    model.addAttribute("user", userService.getUserById(userId));
    return "update-user";
  }

  @PostMapping("update/{id}")
  public String updateUser(@Valid @PathVariable(name = "id") Integer userId,
                           @Valid @ModelAttribute User user) {

    passwordEncoder.encode(user.getPassword());
    userService.updateUser(userId, user);
    return "redirect:/users";
  }
}