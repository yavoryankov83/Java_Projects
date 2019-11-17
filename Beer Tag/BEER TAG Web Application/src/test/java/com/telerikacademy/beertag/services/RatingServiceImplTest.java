package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.RatingRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RatingServiceImplTest {
  @Mock
  RatingRepository ratingRepository;
  @Mock
  BeerRepository beerRepository;
  @Mock
  UserRepository userRepository;
  @InjectMocks
  RatingServiceImpl ratingServiceImpl;

  private Beer beer;
  private User user;
  private Rating rating;
  private Integer ratingValue;
  private DecimalFormat decimalFormat;
  private List<Rating> ratingList;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    beer = Mockito.mock(Beer.class);
    user = Mockito.mock(User.class);
    rating = Mockito.mock(Rating.class);
    ratingValue = 0;
    ratingList = new ArrayList<>();
    ratingList.add(rating);
    when(ratingRepository.getRatingOfBeerByUserAsInt(1, 1))
            .thenReturn(ratingValue);
    when(ratingRepository.findAll()).thenReturn(ratingList);
    String pattern = "#.#";
    decimalFormat = new DecimalFormat(pattern);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateUser_should_throw_exception_when_invalid_user() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.empty());
    when(beerRepository.getById(1)).thenReturn(beer);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    //Act
    ratingServiceImpl.getRatingOfBeerByUser(1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validateBeer_should_throw_exception_when_invalid_beer() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(null);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    //Act
    ratingServiceImpl.getRatingOfBeerByUser(1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void validate_exist_rating_should_throw_exception_when_invalid_rating_object() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(null);
    //Act
    ratingServiceImpl.getRatingOfBeerByUser(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void validate_value_of_rating_should_throw_exception_when_invalid_data() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(null);
    //Act
    ratingServiceImpl.addRatingToBeer(1, 1, -1);
  }

  @Test
  public void get_rating_of_beer_by_given_user_when_data_valid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    //Act
    Rating result = ratingServiceImpl.getRatingOfBeerByUser(1, 1);
    //Assert
    Assert.assertEquals(rating, result);
  }

  @Test
  public void get_ratingValue_of_beer_by_given_user_when_data_valid() {
    //Arrange
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(beerRepository.getById(1)).thenReturn(beer);
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    //Act
    Integer result = ratingServiceImpl.getRatingOfBeerByUserAsInt(1, 1);
    //Assert
    Assert.assertEquals(ratingValue, result);
  }

  @Test
  public void get_average_rating_of_beer_when_data_valid() {
    //Arrange
    when(ratingRepository.getAverageRatingOfBeer(1)).thenReturn(0.0);
    when(beerRepository.getById(1)).thenReturn(beer);
    //Act
    double result = ratingServiceImpl.getAverageRatingOfBeer(1);
    //Assert
    Assert.assertEquals(decimalFormat.format(0.0), decimalFormat.format(result));
  }

  @Test
  public void should_add_rating_to_beer_when_data_valid() {
    //Arrange
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(null);
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //Act
    ratingServiceImpl.addRatingToBeer(1, 1, 1);
    //Assert
    Assert.assertEquals(1, ratingRepository.findAll().size());
  }

  @Test
  public void should_update_rating_to_beer_when_data_valid() {
    //Arrange
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //Act
    ratingServiceImpl.addRatingToBeer(1, 1, 1);
    //Assert
    Assert.assertEquals(1, ratingRepository.findAll().size());
  }

  @Test(expected = EntityNotFoundException.class)
  public void should_throw_exception_when_update_rating_with_invalid_data() {
    //Arrange
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(null);
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //Act
    ratingServiceImpl.updateRating(1, 1, 1);
  }

  @Test(expected = EntityNotFoundException.class)
  public void should_throw_exception_when_delete_rating_with_invalid_data() {
    //Arrange
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(null);
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //Act
    ratingServiceImpl.deleteRating(1, 1);
  }

  @Test
  public void should_delete_rating_to_beer_when_data_valid() {
    //Arrange
    when(ratingRepository.getRatingOfBeerByUser(1, 1))
            .thenReturn(rating);
    when(beerRepository.getById(1)).thenReturn(beer);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    ratingList.remove(rating);
    //Act
    ratingServiceImpl.deleteRating(1, 1);
    //Assert
    Assert.assertEquals(0, ratingRepository.findAll().size());
  }
}

