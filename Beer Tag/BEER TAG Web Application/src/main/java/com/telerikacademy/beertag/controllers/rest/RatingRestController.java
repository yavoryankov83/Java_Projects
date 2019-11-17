package com.telerikacademy.beertag.controllers.rest;

import com.telerikacademy.beertag.services.contracts.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Beer Tag Application")
@RestController
public class RatingRestController {

  private RatingService ratingService;

  @Autowired
  public RatingRestController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @ApiOperation(value = "Get rating of beer", response = Integer.class)
  @GetMapping(path = "api/v1/users/{userId}/beers/{beerId}/rating")
  public int getRatingOfBeerByUser(@Valid @PathVariable(value = "userId") int userId,
                                   @Valid @PathVariable(value = "beerId") int beerId) {
    return ratingService.getRatingOfBeerByUser(userId, beerId).getRatingValue();
  }

  @ApiOperation(value = "Get average rating of beer", response = Double.class)
  @GetMapping(path = "api/v1/beers/{beerId}/avg_rating")
  public double getAverageRatingOfBeer(@Valid @PathVariable(value = "beerId") int beerId) {
    return ratingService.getAverageRatingOfBeer(beerId);
  }

  @ApiOperation(value = "Add rating to beer", response = void.class)
  @PostMapping(path = "api/v1/users/{userId}/beers/{beerId}/addRating/{rating}")
  public void addRatingToBeer(@Valid @PathVariable(value = "userId") int userId,
                              @Valid @PathVariable(value = "beerId") int beerId,
                              @Valid @PathVariable(value = "rating") int rating) {
    ratingService.addRatingToBeer(beerId, userId, rating);
  }

  @ApiOperation(value = "Update rating of beer", response = void.class)
  @PutMapping(path = "api/v1/users/{userId}/beers/{beerId}/updateRating/{rating}")
  public void updateRatingToBeer(@Valid @PathVariable(value = "userId") int userId,
                                 @Valid @PathVariable(value = "beerId") int beerId,
                                 @Valid @PathVariable(value = "rating") int rating) {
    ratingService.updateRating(beerId, userId, rating);
  }

  @ApiOperation(value = "Delete rating of beer", response = void.class)
  @DeleteMapping(path = "api/v1/users/{userId}/beers/{beerId}/deleteRating")
  public void deleteRatingToBeer(@Valid @PathVariable(value = "userId") int userId,
                                 @Valid @PathVariable(value = "beerId") int beerId) {
    ratingService.deleteRating(beerId, userId);
  }
}
