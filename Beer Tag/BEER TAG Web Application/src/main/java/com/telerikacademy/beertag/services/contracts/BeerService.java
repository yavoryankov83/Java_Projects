package com.telerikacademy.beertag.services.contracts;

import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.models.dto.BeerDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BeerService {

  void updateBeer(int beerId, Beer beer);

  void addBeer(Beer beer);

  void addBeerByDTO(BeerDTO beerDTO, Beer beer);

  void deleteBeer(int beerId);

  List<Style> getAllStyles();

  Style getStyleByName(String name);

  List<OriginCountry> getAllCountries();

  OriginCountry getCountriesByName(String name);

  List<Brewery> getAllBreweries();

  Brewery getBreweryByName(String name);

  Beer getBeerById(int beerId);

  List<Beer> getAllBeers();

  List<Beer> getByABV(double abv);

  List<Beer> getByABVBetweenMinAndMax(double minABV, double maxABV);

  List<Beer> sort(Map<String, String> sortParams);

  List<Beer> filter(Map<String, String> filterParameters);

  List<Beer> filterr(Collection<Beer> beers, Map<String, String[]> filterParameters);

  Double getAVG(int beerId);

  void updateBeerByDTO(int beerId, BeerDTO beerDTO);

  BeerDTO convertBeerToDTO(int beerId);

  List<Rating> getRatingsOfBeerByUser(Integer beerId, String username);

  Beer getBeerByName(String name);
}