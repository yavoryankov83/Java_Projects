package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.Status;
import com.telerikacademy.beertag.models.StatusValue;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.StatusRepository;
import com.telerikacademy.beertag.repositories.StatusValueRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import com.telerikacademy.beertag.services.contracts.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

  private static final String NOT_FOUND_EXCEPTION =
          "Entity with id %d not found.";
  private static final String NOT_FOUND_STATUS_EXCEPTION =
          "User with id %d is not marked a beer with id %d.";

  private StatusRepository statusRepository;
  private UserRepository userRepository;
  private BeerRepository beerRepository;
  private StatusValueRepository statusValueRepository;

  @Autowired
  public StatusServiceImpl(StatusRepository statusRepository,
                           UserRepository userRepository,
                           BeerRepository beerRepository,
                           StatusValueRepository statusValueRepository) {
    this.statusRepository = statusRepository;
    this.userRepository = userRepository;
    this.beerRepository = beerRepository;
    this.statusValueRepository = statusValueRepository;
  }

  @Override
  public Status getStatusOfBeerByUser(int userId, int beerId) {
    validateUser(userId);
    validateBeer(beerId);
    validateStatusForNull(userId, beerId);
    return statusRepository.getStatusOfBeerByUser(userId, beerId);
  }

  @Override
  public List<Status> getAllStatusOfBeer(int beerId) {
    validateBeer(beerId);
    return statusRepository.getAllStatusOfBeer(beerId);
  }

  @Override
  public List<StatusValue> getAllStatusValues() {
    return statusValueRepository.getAllStatusValues();
  }

  @Override
  public StatusValue getStatusValueByValue(String statusValue) {
    return statusValueRepository.getStatusValueByValue(statusValue);
  }

  @Override
  public void addStatusToBeer(int userId, int beerId, int statusValueId) {
    Beer beer = validateBeer(beerId);
    User user = validateUser(userId);
    StatusValue statusValue = validateStatusValue(statusValueId);

    Status status = statusRepository.getStatusOfBeerByUser(userId, beerId);
    if (status == null) {
      Status statusToAdd = new Status(beer, user, statusValue);
      beer.addStatus(statusToAdd);
      statusRepository.save(statusToAdd);
    } else {
      updateStatus(userId, beerId, statusValueId);
    }
  }

  @Override
  public void updateStatus(int userId, int beerId, int statusValueId) {
    validateBeer(beerId);
    validateUser(userId);
    StatusValue statusValue = validateStatusValue(statusValueId);

    Status status = statusRepository.getStatusOfBeerByUser(userId, beerId);
    if (status != null) {
      status.setStatusValue(statusValue);
      statusRepository.save(status);
    } else {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
  }

  @Override
  public void deleteStatus(int userId, int beerId) {
    Beer beer = validateBeer(beerId);
    validateUser(userId);
    Status status = statusRepository.getStatusOfBeerByUser(userId, beerId);
    if (status == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, userId));
    }
    beer.deleteStatus(status);
    statusRepository.delete(status);
  }

  private User validateUser(int userId) {
    Optional<User> user = userRepository.findById(userId);
    return user.orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, userId)));
  }

  private Beer validateBeer(int beerId) {
    Beer beer = beerRepository.getById(beerId);
    if (beer == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, beerId));
    }
    return beer;
  }

  private StatusValue validateStatusValue(int statusValueId) {
    StatusValue statusValue = statusValueRepository.getStatusValueById(statusValueId);
    if (statusValue == null) {
      throw new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION, statusValueId));
    }
    return statusValue;
  }

  private void validateStatusForNull(int userId, int beerId) {
    Status status = statusRepository.getStatusOfBeerByUser(userId, beerId);
    if (status == null) {
      throw new EntityNotFoundException(
              String.format(NOT_FOUND_STATUS_EXCEPTION, userId, beerId));
    }
  }
}
