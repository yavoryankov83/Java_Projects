package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.OriginCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginCountryRepository extends JpaRepository<OriginCountry, Integer> {
  List<OriginCountry> findAll();

  @Query(value = "select * from beer_tag.origin_countries oc\n" +
          "where oc.name = :county_name", nativeQuery = true)
  OriginCountry getCountriesByName(@Param(value = "county_name") String county_name);
}
