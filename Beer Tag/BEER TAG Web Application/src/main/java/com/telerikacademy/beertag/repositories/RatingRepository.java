package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
  List<Rating> findAll();

  @Query(value = "select * from beer_tag.ratings r\n" +
          "join beer_tag.beers b on b.id = r.beer_id\n" +
          "join beer_tag.users u on u.id = r.user_id\n" +
          "where u.id = :user_id and b.id = :beer_id", nativeQuery = true)
  Rating getRatingOfBeerByUser(@Param(value = "user_id") int user_id,
                               @Param(value = "beer_id") int beer_id);

  @Query(value = "select r.value from beer_tag.ratings r\n" +
          "join beer_tag.beers b on b.id = r.beer_id\n" +
          "join beer_tag.users u on u.id = r.user_id\n" +
          "where u.id = :user_id and b.id = :beer_id", nativeQuery = true)
  Integer getRatingOfBeerByUserAsInt(@Param(value = "user_id") int user_id,
                                     @Param(value = "beer_id") int beer_id);

  @Query(value = "select round(avg(r.value), 1) from beer_tag.ratings r\n" +
          "where r.beer_id = :beer_id", nativeQuery = true)
  Double getAverageRatingOfBeer(@Param(value = "beer_id") int beer_id);

  void deleteByUserId(int userId);

  void deleteByBeerId(int beerId);
}
