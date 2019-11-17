package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.models.Brewery;
import com.telerikacademy.beertag.repositories.BreweryRepository;
import com.telerikacademy.beertag.services.contracts.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreweryServiceImpl implements BreweryService {
  private static final String CONFLICT_EXCEPTION =
          "Brewery with name %s exists.";

  private BreweryRepository breweryRepository;

  @Autowired
  public BreweryServiceImpl(BreweryRepository breweryRepository) {
    this.breweryRepository = breweryRepository;
  }

  @Override
  public void addBrewery(String name) {

    if (breweryRepository.getBreweryByName(name) != null) {
      throw new EntityConflictException(
              String.format(CONFLICT_EXCEPTION, name));
    }
    Brewery brewery = new Brewery(name);
    breweryRepository.save(brewery);
  }
}
