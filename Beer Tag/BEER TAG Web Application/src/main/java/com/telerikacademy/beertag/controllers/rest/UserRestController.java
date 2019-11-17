package com.telerikacademy.beertag.controllers.rest;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.contracts.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Beer Tag Application")
@RestController
@RequestMapping(path = "api/v1/users")
public class UserRestController {

  private UserService userService;

  @Autowired
  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "View a list of available users", response = List.class)
  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @ApiOperation(value = "View user by id", response = User.class)
  @GetMapping(path = "{id}")
  public User getById(@PathVariable(name = "id") @Valid int userId) {
    return userService.getUserById(userId);
  }

  @ApiOperation(value = "Create user", response = void.class)
  @PostMapping
  public void create(@RequestBody @Valid User user) {
    userService.addUser(user);
  }

  @ApiOperation(value = "Delete user", response = void.class)
  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable(name = "id") @Valid int userId) {
    userService.deleteUser(userId);
  }

  @ApiOperation(value = "Update user", response = void.class)
  @PutMapping(path = "{id}")
  public void update(@PathVariable(name = "id") @Valid int userId, @RequestBody @Valid User user) {
    userService.updateUser(userId, user);
  }

  @ApiOperation(value = "Get top 3 most ranked beers of user", response = List.class)
  @GetMapping(path = "{id}/beers/top3")
  public List<Beer> getTop3MostRankedUserBeers(@PathVariable(name = "id") @Valid int userId) {
    return userService.getTop3MostRankedUserBeers(userId);
  }
}
