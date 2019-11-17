package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.StatusRepository;
import com.telerikacademy.beertag.repositories.StatusValueRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class StatusServiceImplTest {
  @Mock
  StatusRepository statusRepository;
  @Mock
  UserRepository userRepository;
  @Mock
  BeerRepository beerRepository;
  @Mock
  StatusValueRepository statusValueRepository;
  @InjectMocks
  StatusServiceImpl statusServiceImpl;

  private Beer beer;
  private User user;
  private StatusValue statusValue;
  private Status status;
  private List<StatusValue> statusValueList;
  private List<Status> statusList;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    beer = Mockito.mock(Beer.class);
    user = Mockito.mock(User.class);
    statusValue = Mockito.mock(StatusValue.class);
    status = Mockito.mock(Status.class);
    statusList = new ArrayList<>();
    statusList.add(status);
    statusValueList = new ArrayList<>();
    statusValueList.add(statusValue);
    when(statusValueRepository.getAllStatusValues())
            .thenReturn(statusValueList);
    when(statusValueRepository.getStatusValueByValue(anyString()))
            .thenReturn(statusValue);
    when(statusRepository.findAll()).thenReturn(statusList);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateUser_should_throw_exception_when_invalid_user() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.empty());
    when(beerRepository.getById(1)).thenReturn(beer);
    //Act
    statusServiceImpl.getStatusOfBeerByUser(1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateBeer_should_throw_exception_when_invalid_beer() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
    when(beerRepository.getById(1)).thenReturn(null);
    //Act
    statusServiceImpl.getStatusOfBeerByUser(1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateStatusValue_should_throw_exception_when_invalid_status_value() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusValueRepository.findById(1)).thenReturn(null);
    //Act
    statusServiceImpl.addStatusToBeer(1, 1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateStatus_for_null_should_throw_exception_when_invalid_status() {
    //Arrange
    when(userRepository.findById(2)).thenReturn(Optional.of(user));
    when(beerRepository.getById(2)).thenReturn(beer);
    when(statusRepository.getStatusOfBeerByUser(2, 2)).thenReturn(null);
    //Act
    statusServiceImpl.getStatusOfBeerByUser(2, 2);
  }

  @Test
  public void get_status_of_beer_by_user_when_data_valid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(status);
    //Act
    Status result = statusServiceImpl.getStatusOfBeerByUser(1, 1);
    //Assert
    Assert.assertEquals(status, result);
  }

  @Test
  public void get_all_statuses_of_beer_when_data_valid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusRepository.getAllStatusOfBeer(1)).thenReturn(statusList);
    //Act
    List<Status> result = statusServiceImpl.getAllStatusOfBeer(1);
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_all_status_values_when_data_valid() {

    //Act
    List<StatusValue> result = statusServiceImpl.getAllStatusValues();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_statusValue_object_by_given_value() {
    //Act
    StatusValue result = statusServiceImpl.getStatusValueByValue("statusValue");
    //Assert
    Assert.assertEquals(statusValue, result);
  }

  @Test
  public void add_status_to_beer_when_data_valid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusValueRepository.getStatusValueById(1))
            .thenReturn(statusValue);
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(null);
    //Act
    statusServiceImpl.addStatusToBeer(1, 1, 1);
    //Assert
    Assert.assertEquals(1, statusRepository.findAll().size());
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_status_of_beer_when_data_invalid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusValueRepository.getStatusValueById(1))
            .thenReturn(statusValue);
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(null);
    //Act
    statusServiceImpl.updateStatus(1, 1, 1);
  }

  @Test
  public void update_status_of_beer_when_data_valid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(statusValueRepository.getStatusValueById(1))
            .thenReturn(statusValue);
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(status);
    //Act
    statusServiceImpl.addStatusToBeer(1, 1, 1);
    //Assert
    Assert.assertEquals(1, statusRepository.findAll().size());
  }

  @Test(expected = EntityNotFoundException.class)
  public void delete_status_from_beer_when_data_invalid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(null);
    //Act
    statusServiceImpl.deleteStatus(1, 1);
  }

  @Test
  public void delete_status_from_beer_when_data_valid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    when(statusRepository.getStatusOfBeerByUser(1, 1)).thenReturn(status);
    statusList.remove(status);
    //Act
    statusServiceImpl.deleteStatus(1, 1);
    //Assert
    Assert.assertEquals(0, statusRepository.findAll().size());
  }
}