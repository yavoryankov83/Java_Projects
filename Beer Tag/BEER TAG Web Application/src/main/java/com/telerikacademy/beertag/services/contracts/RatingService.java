package com.telerikacademy.beertag.services.contracts;

import com.telerikacademy.beertag.models.Rating;

public interface RatingService {

  Rating getRatingOfBeerByUser(int userId, int beerId);

  Integer getRatingOfBeerByUserAsInt(int userId, int beerId);

  double getAverageRatingOfBeer(int beerId);

  void addRatingToBeer(int beerId, int userId, int rating);

  void updateRating(int beerId, int userId, int rating);

  void deleteRating(int beerId, int userId);
}
