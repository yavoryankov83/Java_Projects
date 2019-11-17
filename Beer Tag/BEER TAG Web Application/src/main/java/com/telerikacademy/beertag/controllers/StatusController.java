package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.services.contracts.BeerService;
import com.telerikacademy.beertag.services.contracts.StatusService;
import com.telerikacademy.beertag.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping
public class StatusController {
  private StatusService statusService;
  private BeerService beerService;
  private UserService userService;

  @Autowired
  public StatusController(StatusService statusService,
                          BeerService beerService,
                          UserService userService) {
    this.statusService = statusService;
    this.beerService = beerService;
    this.userService = userService;
  }

  @GetMapping(path = "beers/{beerId}/status")
  public String getStatusOfBeerByUser(Model model, Principal principal,
                                      @Valid @PathVariable(name = "beerId") int beerId) {
    String username = principal.getName();
    User user = userService.getUserByUsername(username);
    model.addAttribute("status", statusService
            .getStatusOfBeerByUser(user.getId(), beerId).getStatusValue());
    model.addAttribute("getBeer", beerService.getBeerById(beerId));
    return "get-beer";
  }

  @GetMapping(path = "beers/{beerId}/addStatus")
  public String addStatusToBeer(Model model, @Valid @PathVariable(name = "beerId") int beerId) {
    model.addAttribute("status", new StatusValue());
    model.addAttribute("beer", beerService.getBeerById(beerId));
    model.addAttribute("statusValues", statusService.getAllStatusValues());
    return "create-status";
  }

  @PostMapping(path = "beers/{beerId}/addStatus")
  public String addRatingToBeer(Model model, Principal principal,
                                @Valid @PathVariable(name = "beerId") int beerId,
                                @Valid @ModelAttribute("status") StatusValue modelValue,
                                BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "create-status";
    }
    String username = principal.getName();
    User user = userService.getUserByUsername(username);
    Beer beer = beerService.getBeerById(beerId);
    StatusValue statusValue = statusService.getStatusValueByValue(modelValue.getStatusValue());
    statusService.addStatusToBeer(user.getId(), beer.getId(), statusValue.getId());
    model.addAttribute("beers", beerService.getAllBeers());
    return "redirect:/beers/{beerId}";
  }
}
