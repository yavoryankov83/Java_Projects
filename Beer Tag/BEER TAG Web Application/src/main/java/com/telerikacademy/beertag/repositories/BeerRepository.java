package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {
  Optional<Beer> findByName(String name);

  boolean existsByName(String beerName);

  List<Beer> getByAbv(double abv);

  List<Beer> getByAbvBetween(double minABV, double maxABV);

  @Query(value = "select * from beer_tag.beers b\n" +
          "where b.id = :beer_id", nativeQuery = true)
  Beer getById(@Param(value = "beer_id") int beer_id);

  @Query(value = "select * from beer_tag.beers b\n" +
          "join beer_tag.beers_tags bt on b.id = bt.beer_id\n" +
          "join beer_tag.tags t on bt.tag_id = t.id\n" +
          "where t.name = :tag", nativeQuery = true)
  List<Beer> getByTag(@Param(value = "tag") String tag);

  @Query(value = "select * from beer_tag.beers b\n" +
          "join beer_tag.styles s on b.style_id = s.id\n" +
          "where s.name = :style", nativeQuery = true)
  List<Beer> getByStyle(@Param(value = "style") String style);

  @Query(value = "select * from beer_tag.beers b\n" +
          "join beer_tag.origin_countries o on b.country_id = o.id\n" +
          "where o.name = :country", nativeQuery = true)
  List<Beer> getByOriginCountry(@Param(value = "country") String country);

  @Query(value = "select * from beer_tag.beers b " +
          "join beer_tag.ratings r on r.beer_id = b.id\n" +
          "where r.value = :rating", nativeQuery = true)
  List<Beer> getByRating(@Param(value = "rating") int rating);

  @Query(value = "select * from beer_tag.beers b\n" +
          "join beer_tag.statuses s on b.id = s.beer_id\n" +
          "where s.status_value_id = ?1", nativeQuery = true)
  List<Beer> getByStatusValue(Integer statusValueId);

  void deleteById(Integer beerId);
}
