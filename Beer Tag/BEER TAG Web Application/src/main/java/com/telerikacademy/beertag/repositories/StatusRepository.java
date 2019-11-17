package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
  @Query(value = "select * from beer_tag.statuses s\n" +
          "join beer_tag.users u on s.user_id = u.id\n" +
          "join beer_tag.beers b on s.beer_id = b.id\n" +
          "where u.id = :user_id and b.id = :beer_id", nativeQuery = true)
  Status getStatusOfBeerByUser(@Param(value = "user_id") int user_id,
                               @Param(value = "beer_id") int beer_id);

  @Query(value = "select * from beer_tag.statuses s\n" +
          "join beer_tag.beers b on s.beer_id = b.id\n" +
          "where b.id = :beer_id", nativeQuery = true)
  List<Status> getAllStatusOfBeer(@Param(value = "beer_id") int beer_id);

  void deleteByUserId(int useId);

  void deleteByBeerId(int beerId);
}
