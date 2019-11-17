package com.telerikacademy.beertag.services.contracts;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;

import java.util.List;

public interface UserService {

  List<User> getAll();

  User getUserById(int userId);

  User getUserByUsername(String username);

  List<Beer> getTop3MostRankedUserBeers(int userId);

  void addUser(User user);

  void deleteUser(int userId);

  void updateUser(int userId, User user);

  boolean isUserEmailExist(String email);
}
