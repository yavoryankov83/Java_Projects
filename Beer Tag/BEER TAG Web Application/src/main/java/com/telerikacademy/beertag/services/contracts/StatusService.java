package com.telerikacademy.beertag.services.contracts;

import com.telerikacademy.beertag.models.Status;
import com.telerikacademy.beertag.models.StatusValue;

import java.util.List;

public interface StatusService {

  List<StatusValue> getAllStatusValues();

  Status getStatusOfBeerByUser(int userId, int beerId);

  List<Status> getAllStatusOfBeer(int beerId);

  StatusValue getStatusValueByValue(String statusValue);

  void addStatusToBeer(int userId, int beerId, int statusId);

  void updateStatus(int userId, int beerId, int statusId);

  void deleteStatus(int userId, int beerId);

}
