package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityBadRequestException;
import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.exceptions.EntityEmailExistException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.RatingRepository;
import com.telerikacademy.beertag.repositories.StatusRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
  @Mock
  UserRepository userRepository;
  @Mock
  RatingRepository ratingRepository;
  @Mock
  StatusRepository statusRepository;
  @Mock
  BeerRepository beerRepository;
  @InjectMocks
  UserServiceImpl userServiceImpl;

  private Beer beer;
  private User user;
  private List<User> userList;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    user = Mockito.mock(User.class);
    beer = Mockito.mock(Beer.class);
    userList = new ArrayList<>();
    userList.add(user);
    when(userRepository.findAllByEnabledTrue()).thenReturn(userList);
  }

  @Test
  public void get_all_should_return_all_users() {
    //Act
    List<User> result = userServiceImpl.getAll();
    //Assert
    Assert.assertEquals(userList, result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void should_throw_exception_when_user_not_exists() {
    //Arrange
    when(userRepository.findByIdAndEnabledTrue(1)).thenReturn(Optional.empty());
    //Act
    userServiceImpl.getUserById(1);
  }

  @Test
  public void get_user_by_id_should_return_user_with_valid_id() {
    //Arrange
    when(userRepository.findByIdAndEnabledTrue(1)).thenReturn(Optional.of(user));
    //Act
    User result = userServiceImpl.getUserById(1);
    //Assert
    Assert.assertEquals(Optional.of(user).get(), result);
  }

  @Test
  public void get_user_by_username_should_return_user_with_valid_username() {
    //Arrange
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(user));
    //Act
    User result = userServiceImpl.getUserByUsername("username");
    //Assert
    Assert.assertEquals(Optional.of(user).get(), result);
  }

  @Test(expected = EntityConflictException.class)
  public void should_throw_exception_when_add_existing_user() {
    //Arrange
    when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
    //Act
    userServiceImpl.addUser(user);
  }

  @Test
  public void should_add_user_when_data_valid() {
    //Arrange
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
    when(user.getPassword()).thenReturn("password");
    when(user.getPasswordConfirmation()).thenReturn("password");
    //Act
    userServiceImpl.addUser(user);
    //Assert
    Assert.assertEquals(1, userRepository.findAllByEnabledTrue().size());
  }

  @Test(expected = EntityBadRequestException.class)
  public void should_throw_exception_to_add_user_when_data_invalid() {
    //Arrange
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
    when(user.getPassword()).thenReturn("password");
    when(user.getPasswordConfirmation()).thenReturn("password2");
    //Act
    userServiceImpl.addUser(user);
  }

  @Test
  public void should_update_user_when_data_valid() {
    //Arrange
    User userToUpdate = new User("userToUpdate", "myEmail", "Pass", "");
    User user = new User("user", "myEmail_2", "Pass", "photo");
    List<User> getAll = new ArrayList<>();
    getAll.add(userToUpdate);
    when(userRepository.findByIdAndEnabledTrue(1))
            .thenReturn(Optional.of(userToUpdate));
    when(userRepository.findByEmailAndEnabledTrue("myEmail_2")).thenReturn(Optional.empty());

    //Act
    userServiceImpl.updateUser(1, user);
    //Assert
    Assert.assertEquals(1, userRepository.findAllByEnabledTrue().size());
  }


  @Test(expected = EntityEmailExistException.class)
  public void should_throw_exception_when_update_with_existing_email() {
    //Arrange
    User userToUpdate = new User("userToUpdate", "myEmail", "Pass", "");
    List<User> getAll = new ArrayList<>();
    getAll.add(userToUpdate);
    when(userRepository.findByIdAndEnabledTrue(1))
            .thenReturn(Optional.of(userToUpdate));
    when(userRepository.findByEmailAndEnabledTrue("myEmail")).thenReturn(Optional.of(userToUpdate));
    //Act
    userServiceImpl.updateUser(1, user);

  }

  @Test
  public void should_delete_user_when_data_valid() {
    //Arrange
    when(userRepository.findByIdAndEnabledTrue(anyInt())).thenReturn(Optional.ofNullable(user));
    doNothing().when(ratingRepository).deleteByUserId(1);
    doNothing().when(statusRepository).deleteByUserId(1);
    //Act
    userServiceImpl.deleteUser(1);
    //Assert
    Assert.assertTrue(!user.getEnabled());
  }

  @Test
  public void get_top3_most_ranked_beers_of_user() {
    //Arrange
    List<Integer> listBeersId = new ArrayList<>();
    listBeersId.add(beer.getId());
    when(userRepository.top3MostRankedUserBeers(anyInt())).thenReturn(listBeersId);
    when(beerRepository.getById(anyInt())).thenReturn(beer);
    //Act
    List<Beer> result = userServiceImpl.getTop3MostRankedUserBeers(1);
    //Assert
    Assert.assertEquals(1, result.size());
  }
}

