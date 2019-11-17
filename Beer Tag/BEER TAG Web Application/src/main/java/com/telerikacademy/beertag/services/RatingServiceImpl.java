package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.Rating;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.RatingRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import com.telerikacademy.beertag.services.contracts.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

  private static final String NOT_FOUND_EXCEPTION =
          "Entity with id %d not found.";
  private static final String INVALID_RATING =
          "Rating value is between 1 and 10.";
  private static final String NOT_FOUND_RATING_EXCEPTION =
          "Beer with with id %d is not rated by user with id %d.";

  private RatingRepository ratingRepository;
  private BeerRepository beerRepository;
  private UserRepository userRepository;

  @Autowired
  public RatingServiceImpl(RatingRepository ratingRepository,
                           BeerRepository beerRepository,
                           UserRepository userRepository) {
    this.ratingRepository = ratingRepository;
    this.beerRepository = beerRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Rating getRatingOfBeerByUser(int userId, int beerId) {
    validateBeer(beerId);
    validateUser(userId);
    validateRatingForNull(userId, beerId);
    return ratingRepository.getRatingOfBeerByUser(userId, beerId);
  }

  @Override
  public Integer getRatingOfBeerByUserAsInt(int userId, int beerId) {
    validateBeer(beerId);
    validateUser(userId);
    validateRatingForNull(userId, beerId);
    return ratingRepository.getRatingOfBeerByUserAsInt(userId, beerId);
  }

  @Override
  public double getAverageRatingOfBeer(int beerId) {
    validateBeer(beerId);
    return ratingRepository.getAverageRatingOfBeer(beerId);
  }

  @Override
  @Transactional
  public void addRatingToBeer(int beerId, int userId, int rating) {
    Beer beer = validateBeer(beerId);
    User user = validateUser(userId);
    validateRating(rating);

    Rating currentRating = ratingRepository.getRatingOfBeerByUser(userId, beerId);
    if (currentRating == null) {
      Rating ratingToAdd = new Rating(rating, beer, user);
      beer.addRating(ratingToAdd);
      user.addRating(ratingToAdd);
      ratingRepository.save(ratingToAdd);
    } else {
      updateRating(beerId, userId, rating);
    }
  }

  @Override
  @Transactional
  public void updateRating(int beerId, int userId, int rating) {
    validateBeer(beerId);
    validateUser(userId);
    validateRating(rating);

    Rating ratingToUpdate = ratingRepository.getRatingOfBeerByUser(userId, beerId);
    if (ratingToUpdate != null) {
      ratingToUpdate.setRatingValue(rating);
      ratingRepository.save(ratingToUpdate);
    } else {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
  }

  @Override
  public void deleteRating(int beerId, int userId) {
    Beer beer = validateBeer(beerId);
    User user = validateUser(userId);
    Rating ratingToDelete = ratingRepository.getRatingOfBeerByUser(userId, beerId);
    if (ratingToDelete == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
    beer.deleteRating(ratingToDelete);
    user.deleteRating(ratingToDelete);
    ratingRepository.delete(ratingToDelete);
  }

  private User validateUser(int userId) {
    Optional<User> user = userRepository.findById(userId);
    return user.orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, userId)));
  }

  private Beer validateBeer(int beerId) {
    Beer beer = beerRepository.getById(beerId);
    if (beer == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
    return beer;
  }

  private void validateRating(int rating) {
    if (rating <= 0 || rating > 10) {
      throw new IllegalArgumentException(INVALID_RATING);
    }
  }

  private void validateRatingForNull(int userId, int beerId) {
    Rating currentRating = ratingRepository.getRatingOfBeerByUser(userId, beerId);
    if (currentRating == null) {
      throw new EntityNotFoundException(
              String.format(NOT_FOUND_RATING_EXCEPTION, beerId, userId));
    }
  }
}
