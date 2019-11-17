package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityBadRequestException;
import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.exceptions.EntityEmailExistException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.RatingRepository;
import com.telerikacademy.beertag.repositories.StatusRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import com.telerikacademy.beertag.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private static final String EMAIL_EXIST_EXCEPTION =
          "Email already exists.";
  private static final String NOT_FOUND_EXCEPTION =
          "User not found.";
  private static final String CONFLICT_EXCEPTION =
          "User exists.";
  private static final String BAD_REQUEST_EXCEPTION =
          "Password and passwordconfirmation should match.";

  private UserRepository userRepository;
  private RatingRepository ratingRepository;
  private StatusRepository statusRepository;
  private BeerRepository beerRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RatingRepository ratingRepository, StatusRepository statusRepository, BeerRepository beerRepository) {
    this.userRepository = userRepository;
    this.ratingRepository = ratingRepository;
    this.statusRepository = statusRepository;
    this.beerRepository = beerRepository;
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAllByEnabledTrue();
  }

  @Override
  public User getUserById(int userId) {
    if (userRepository.findByIdAndEnabledTrue(userId).isPresent()) {
      return userRepository.findByIdAndEnabledTrue(userId).get();
    } else {
      throw new EntityNotFoundException(NOT_FOUND_EXCEPTION);
    }
  }

  @Override
  public User getUserByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return user
            .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EXCEPTION));
  }

  @Override
  @Transactional
  public void addUser(User user) {
    boolean isUserExists = userRepository.existsByUsername(user.getUsername());
    if (isUserExists) {
      throw new EntityConflictException(CONFLICT_EXCEPTION);
    }
    if (!user.getPassword().equals(user.getPasswordConfirmation())) {
      throw new EntityBadRequestException(BAD_REQUEST_EXCEPTION);
    }
    User userToCreate = new User(user.getUsername(),
            user.getEmail(),
            user.getPassword(), user.getPhoto());
    userRepository.save(userToCreate);
  }

  @Override
  @Transactional
  public void updateUser(int userId, User user) {
    User userToUpdate = getUserById(userId);
    if (user.getEmail() != null && !isUserEmailExist(user.getEmail())) {
      userToUpdate.setEmail(user.getEmail());
    } else {
      throw new EntityEmailExistException(EMAIL_EXIST_EXCEPTION);
    }
    if (user.getPhoto() != null) {
      userToUpdate.setPhoto(user.getPhoto());
    }
    userRepository.save(userToUpdate);
  }

  @Override
  @Transactional
  public void deleteUser(int userId) {
    User userToDelete = getUserById(userId);
    ratingRepository.deleteByUserId(userId);
    statusRepository.deleteByUserId(userId);
    userToDelete.setEnabled(false);
    userRepository.save(userToDelete);
  }

  @Override
  public List<Beer> getTop3MostRankedUserBeers(int userId) {
    List<Integer> beerIds = userRepository.top3MostRankedUserBeers(userId);
    List<Beer> top3Beers = new ArrayList<>();
    for (Integer id : beerIds) {
      top3Beers.add(beerRepository.getById(id));
    }
    return top3Beers;
  }

  @Override
  public boolean isUserEmailExist(String email) {
    Optional<User> userByEmail = userRepository.findByEmailAndEnabledTrue(email);
    return userByEmail.isPresent();
  }
}
