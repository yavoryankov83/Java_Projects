package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Integer> {
  List<Brewery> findAll();

  @Query(value = "select * from beer_tag.breweries b\n" +
          "where b.name = :brewery_name", nativeQuery = true)
  Brewery getBreweryByName(@Param(value = "brewery_name") String brewery_name);
}
