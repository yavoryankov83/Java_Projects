package com.telerikacademy.beertag.controllers.rest;

import com.telerikacademy.beertag.models.Status;
import com.telerikacademy.beertag.services.contracts.StatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Beer Tag Application")
@RestController
public class StatusRestController {

  private StatusService statusService;

  @Autowired
  public StatusRestController(StatusService statusService) {
    this.statusService = statusService;
  }

  @ApiOperation(value = "Get status of beer", response = Status.class)
  @GetMapping(path = "api/v1/users/{userId}/beers/{beerId}/status")
  public Status getStatusByUserToBeer(@Valid @PathVariable(value = "userId") int userId,
                                      @Valid @PathVariable(value = "beerId") int beerId) {
    return statusService.getStatusOfBeerByUser(userId, beerId);
  }

  @ApiOperation(value = "Get all statuses of beer", response = List.class)
  @GetMapping(path = "api/v1/beers/{beerId}/allStatuses")
  public List<Status> getAllStatusOfBeer(@Valid @PathVariable(value = "beerId") int beerId) {
    return statusService.getAllStatusOfBeer(beerId);
  }

  @ApiOperation(value = "Add status to beer beer", response = void.class)
  @PostMapping(path = "api/v1/users/{userId}/beers/{beerId}/addStatus/{statusValueId}")
  public void addStatusByUserToBeer(@Valid @PathVariable(value = "userId") int userId,
                                    @Valid @PathVariable(value = "beerId") int beerId,
                                    @Valid @PathVariable(value = "statusValueId") int statusValueId) {
    statusService.addStatusToBeer(userId, beerId, statusValueId);
  }

  @ApiOperation(value = "Update status of beer", response = void.class)
  @PutMapping(path = "api/v1/users/{userId}/beers/{beerId}/updateStatus/{statusValueId}")
  public void updateStatusOfBeer(@Valid @PathVariable(value = "userId") int userId,
                                 @Valid @PathVariable(value = "beerId") int beerId,
                                 @Valid @PathVariable(value = "statusValueId") int statusValueId) {
    statusService.updateStatus(userId, beerId, statusValueId);
  }

  @ApiOperation(value = "Delete status of beer", response = void.class)
  @DeleteMapping(path = "api/v1/users/{userId}/beers/{beerId}/deleteStatus")
  public void deleteStatusOfBeer(@Valid @PathVariable(value = "userId") int userId,
                                 @Valid @PathVariable(value = "beerId") int beerId) {
    statusService.deleteStatus(userId, beerId);
  }
}
