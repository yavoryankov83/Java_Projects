package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String name);

  Optional<User> findByIdAndEnabledTrue(int userId);

  Optional<User> findByEmailAndEnabledTrue(String email);

  List<User> findAllByEnabledTrue();

  Optional<User> findByUsername(String username);

  @Query(value = "select b.id from beer_tag.beers b\n" +
          "join beer_tag.ratings r on b.id = r.beer_id\n" +
          "join beer_tag.users u on r.user_id = u.id\n" +
          "where u.id = ?1\n" +
          "order by r.value desc\n" +
          "limit 3", nativeQuery = true)
  List<Integer> top3MostRankedUserBeers(int userId);
}
