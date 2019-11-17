package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityBadRequestException;
import com.telerikacademy.beertag.exceptions.EntityBeerExistException;
import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.models.dto.BeerDTO;
import com.telerikacademy.beertag.repositories.*;
import com.telerikacademy.beertag.services.contracts.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

  private static final String NOT_FOUND_EXCEPTION =
          "Beer with id %d not found.";
  private static final String NAME_NOT_FOUND_EXCEPTION =
          "Beer with name %s not found.";
  private static final String CONFLICT_EXCEPTION =
          "Beer with id %d exists.";
  private static final String BAD_REQUEST_EXCEPTION =
          "Input parameters should be valid.";
  private static final String BEER_EXIST_EXCEPTION =
          "Beer with given name already exists.";

  private BeerRepository beerRepository;
  private StyleRepository styleRepository;
  private OriginCountryRepository originCountryRepository;
  private BreweryRepository breweryRepository;
  private RatingRepository ratingRepository;
  private BeerTagRepository beerTagRepository;
  private StatusRepository statusRepository;

  @Autowired
  public BeerServiceImpl(BeerRepository beerRepository,
                         StyleRepository styleRepository,
                         OriginCountryRepository originCountryRepository,
                         BreweryRepository breweryRepository,
                         RatingRepository ratingRepository,
                         BeerTagRepository beerTagRepository,
                         StatusRepository statusRepository) {
    this.beerRepository = beerRepository;
    this.styleRepository = styleRepository;
    this.originCountryRepository = originCountryRepository;
    this.breweryRepository = breweryRepository;
    this.ratingRepository = ratingRepository;
    this.beerTagRepository = beerTagRepository;
    this.statusRepository = statusRepository;
  }

  @Override
  public List<Beer> getAllBeers() {
    return beerRepository.findAll();
  }

  @Override
  public List<Style> getAllStyles() {
    return styleRepository.findAll();
  }

  @Override
  public Style getStyleByName(String name) {
    return styleRepository.getStyleByName(name);
  }

  @Override
  public List<OriginCountry> getAllCountries() {
    return originCountryRepository.findAll();
  }

  @Override
  public OriginCountry getCountriesByName(String name) {
    return originCountryRepository.getCountriesByName(name);
  }

  @Override
  public List<Brewery> getAllBreweries() {
    return breweryRepository.findAll();
  }

  @Override
  public Brewery getBreweryByName(String name) {
    return breweryRepository.getBreweryByName(name);
  }

  @Override
  public Beer getBeerById(int beerId) {
    Beer beer = beerRepository.getById(beerId);
    if (beer == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
    return beer;
  }

  @Override
  public Double getAVG(int beerId) {
    Double averageRating = ratingRepository.getAverageRatingOfBeer(beerId);
    if (averageRating == null) {
      return 0.0;
    }
    return averageRating;
  }

  @Override
  public List<Beer> getByABV(double abv) {
    return beerRepository.getByAbv(abv);
  }

  @Override
  public List<Beer> getByABVBetweenMinAndMax(double minABV, double maxABV) {
    return beerRepository.getByAbvBetween(minABV, maxABV);
  }

  @Override
  public BeerDTO convertBeerToDTO(int beerId) {
    boolean beerExistById = beerRepository.existsById(beerId);
    if (!beerExistById) {
      throw new EntityConflictException(
              String.format(CONFLICT_EXCEPTION, beerId));
    }
    Beer beer = beerRepository.getById(beerId);
    BeerDTO beerDTO = new BeerDTO();
    beerDTO.setName(beer.getName());
    beerDTO.setAbv(Double.toString(beer.getAbv()));
    beerDTO.setDescription(beer.getDescription());
    beerDTO.setPhoto(beer.getPhoto());
    beerDTO.setOriginCountry(beer.getOriginCountry().getName());
    beerDTO.setStyle(beer.getStyle().getName());
    beerDTO.setBrewery(beer.getBrewery().getName());

    return beerDTO;
  }

  @Transactional
  @Override
  public void addBeer(Beer beer) {
    boolean beerExistByName = beerRepository.existsByName(beer.getName());
    if (beerExistByName) {
      throw new EntityBeerExistException(BEER_EXIST_EXCEPTION);
    }

    Beer beerToCreate = new Beer(beer.getName(),
            beer.getAbv(),
            beer.getDescription(),
            beer.getPhoto(),
            beer.getBrewery(),
            beer.getStyle(),
            beer.getOriginCountry());
    beerRepository.save(beerToCreate);
  }

  @Override
  public void addBeerByDTO(BeerDTO beerDTO, Beer beer) {
    boolean beerExistByName = beerRepository.existsByName(beerDTO.getName());
    if (beerExistByName) {
      throw new EntityBeerExistException(BEER_EXIST_EXCEPTION);
    }
    beer.setName(beerDTO.getName());
    beer.setAbv(Double.parseDouble(beerDTO.getAbv()));
    if (beerDTO.getDescription() != null) {
      beer.setDescription(beerDTO.getDescription());
    }
    if (beerDTO.getPhoto() != null) {
      beer.setPhoto(beerDTO.getPhoto());
    }
    beer.setOriginCountry(getCountriesByName(beerDTO.getOriginCountry()));
    beer.setStyle(getStyleByName(beerDTO.getStyle()));
    beer.setBrewery(getBreweryByName(beerDTO.getBrewery()));

    beerRepository.save(beer);
  }

  @Override
  public void updateBeer(int beerId, Beer beer) {
    Beer beerToUpdate = getBeerById(beerId);
    if (beer.getName() != null) {
      beerToUpdate.setName(beer.getName());
    }
    if (beer.getAbv() != 0.0) {
      beerToUpdate.setAbv(beer.getAbv());
    }
    if (beer.getDescription() != null) {
      beerToUpdate.setDescription(beer.getDescription());
    }
    if (beer.getPhoto() != null) {
      beerToUpdate.setPhoto(beer.getPhoto());
    }
    if (beer.getBrewery() != null) {
      beerToUpdate.setBrewery(beer.getBrewery());
    }
    if (beer.getStyle() != null) {
      beerToUpdate.setStyle(beer.getStyle());
    }
    if (beer.getOriginCountry() != null) {
      beerToUpdate.setOriginCountry(beer.getOriginCountry());
    }
    beer.setTags(beer.getTags());
    beerRepository.save(beerToUpdate);
  }

  @Override
  public void updateBeerByDTO(int beerId, BeerDTO beerDTO) {
    boolean beerExistById = beerRepository.existsById(beerId);
    if (!beerExistById) {
      throw new EntityConflictException(
              String.format(CONFLICT_EXCEPTION, beerId));
    }
    Beer beer = beerRepository.getById(beerId);
    beer.setName(beerDTO.getName());
    beer.setAbv(Double.parseDouble(beerDTO.getAbv()));
    beer.setDescription(beerDTO.getDescription());
    beer.setPhoto(beerDTO.getPhoto());
    beer.setOriginCountry(getCountriesByName(beerDTO.getOriginCountry()));
    beer.setStyle(getStyleByName(beerDTO.getStyle()));
    beer.setBrewery(getBreweryByName(beerDTO.getBrewery()));

    beerRepository.save(beer);
  }

  @Transactional
  @Override
  public void deleteBeer(int beerId) {
    Beer beerToDelete = getBeerById(beerId);
    ratingRepository.deleteByBeerId(beerId);
    statusRepository.deleteByBeerId(beerId);
    beerTagRepository.deleteByBeerId(beerId);
    beerToDelete.getTags().clear();
    beerToDelete.getRatings().clear();
    beerToDelete.getStatuses().clear();
    beerRepository.delete(beerToDelete);
  }

  @Override
  public List<Beer> sort(Map<String, String> sortParameters) {
    List<Beer> result = getAllBeers();

    if (sortParameters.containsKey("sort")) {
      if (sortParameters.get("sort").equals("name_asc")) {
        result = result.stream()
                .sorted(Comparator.comparing(Beer::getName))
                .collect(Collectors.toList());
      }
      if (sortParameters.get("sort").equals("name_desc")) {
        result = result.stream()
                .sorted(Comparator.comparing(Beer::getName).reversed())
                .collect(Collectors.toList());
      }
      if (sortParameters.get("sort").equals("abv_asc")) {
        result = result.stream()
                .sorted(Comparator.comparing(Beer::getAbv))
                .collect(Collectors.toList());
      }
      if (sortParameters.get("sort").equals("abv_desc")) {
        result = result.stream()
                .sorted(Comparator.comparing(Beer::getAbv).reversed())
                .collect(Collectors.toList());
      }
      if (sortParameters.get("sort").equals("rating_asc")) {
        result = result.stream()
                .filter(beer -> beer.getAverageRating() > 0)
                .sorted(Comparator.comparing(Beer::getAverageRating))
                .collect(Collectors.toList());
      }
      if (sortParameters.get("sort").equals("rating_desc")) {
        result = result.stream()
                .filter(beer -> beer.getAverageRating() > 0)
                .sorted(Comparator.comparing(Beer::getAverageRating).reversed())
                .collect(Collectors.toList());
      }
    }
    return result;
  }

  @Override
  public List<Beer> filter(Map<String, String> filterParameters) {
    String tag = filterParameters.get("tag");
    String style = filterParameters.get("style");
    String country = filterParameters.get("country");
    String rating = filterParameters.get("rating");
    String status = filterParameters.get("status");

    if (tag != null) {
      return beerRepository.getByTag(tag);
    }

    if (style != null) {
      return beerRepository.getByStyle(style);
    }
    if (country != null) {
      return beerRepository.getByOriginCountry(country);
    }
    if (rating != null) {
      return beerRepository.getByRating(Integer.parseInt(rating));
    }
    if (status != null) {
      return beerRepository.getByStatusValue(Integer.parseInt(status));
    }
    throw new EntityBadRequestException(BAD_REQUEST_EXCEPTION);
  }

  @Override
  public List<Beer> filterr(Collection<Beer> beers, Map<String, String[]> filterParameters) {
    List<Beer> result = getAllBeers();

    if (filterParameters.containsKey("tag")
            && Arrays
            .stream(filterParameters.get("tag"))
            .noneMatch(s -> s.equals("NONE"))) {
      String[] tags = filterParameters.get("tag");
      List<Beer> beerList = new ArrayList<>();
      for (Beer beer : result) {
        for (String tag : tags) {
          if (beer.getTags().stream().anyMatch(tag1 -> tag1.getTag().getName().equals(tag))) {
            beerList.add(beer);
          }
        }
      }
      result = beerList;
    }

    if (filterParameters.containsKey("style")
            && Arrays
            .stream(filterParameters.get("style"))
            .noneMatch(s -> s.equals("NONE"))) {
      String[] styles = filterParameters.get("style");
      List<Beer> beerList = new ArrayList<>();
      for (Beer beer : result) {
        for (String style : styles) {
          if (beer.getStyle().getName().equals(style)) {
            beerList.add(beer);
          }
        }
      }
      result = beerList;
    }

    if (filterParameters.containsKey("country")
            && Arrays
            .stream(filterParameters.get("country"))
            .noneMatch(s -> s.equals("NONE"))) {
      String[] countries = filterParameters.get("country");
      List<Beer> beerList = new ArrayList<>();
      for (Beer beer : result) {
        for (String country : countries) {
          if (beer.getOriginCountry().getName().equals(country)) {
            beerList.add(beer);
          }
        }
      }
      result = beerList;
    }

    if (filterParameters.containsKey("rating")
            && Arrays
            .stream(filterParameters.get("rating"))
            .noneMatch(s -> s.equals("0"))) {
      String[] ratings = filterParameters.get("rating");
      List<Beer> beerList = new ArrayList<>();
      for (Beer beer : result) {
        for (String rating : ratings) {
          if (beer.getRatings()
                  .stream()
                  .anyMatch(rating1 -> rating1.getRatingValue() == Integer.parseInt(rating))) {
            beerList.add(beer);
          }
        }
      }
      result = beerList;
    }

    if (filterParameters.containsKey("status")
            && Arrays
            .stream(filterParameters.get("status"))
            .noneMatch(s -> s.equals("NONE"))) {
      String[] statuses = filterParameters.get("status");
      List<Beer> beerList = new ArrayList<>();
      for (Beer beer : result) {
        for (String status : statuses) {
          if (beer.getStatuses()
                  .stream()
                  .anyMatch(status1 -> status1.getStatusValue().getStatusValue().equals(status))) {
            beerList.add(beer);
          }
        }
      }
      result = beerList;
    }

    return result;
  }

  @Override
  public List<Rating> getRatingsOfBeerByUser(Integer beerId, String username) {
    return getBeerById(beerId)
            .getRatings()
            .stream()
            .filter(rating -> rating.getUser().getUsername().equals(username))
            .collect(Collectors.toList());
  }

  @Override
  public Beer getBeerByName(String name) {
    Optional<Beer> beer = beerRepository.findByName(name);
    if (!beer.isPresent()) {
      throw new EntityNotFoundException(String.format(NAME_NOT_FOUND_EXCEPTION, name));
    }
    return beer.get();
  }
}