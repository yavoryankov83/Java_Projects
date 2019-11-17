package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.Rating;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.contracts.BeerService;
import com.telerikacademy.beertag.services.contracts.RatingService;
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
public class RatingController {
  private RatingService ratingService;
  private BeerService beerService;
  private UserService userService;

  @Autowired
  public RatingController(RatingService ratingService, BeerService beerService, UserService userService) {
    this.ratingService = ratingService;
    this.beerService = beerService;
    this.userService = userService;
  }

  @GetMapping(path = "beers/{beerId}/rating")
  public String getRatingOfBeerByUser(Model model, Principal principal,
                                      @Valid @PathVariable(name = "beerId") int beerId) {
    String username = principal.getName();
    User user = userService.getUserByUsername(username);
    model.addAttribute("rating", ratingService.getRatingOfBeerByUser(user.getId(), beerId).getRatingValue());
    model.addAttribute("getBeer", beerService.getBeerById(beerId));
    return "get-beer";
  }

  @GetMapping(path = "beers/{beerId}/avg_rating")
  public String getAverageRatingOfBeer(Model model, @Valid @PathVariable(name = "beerId") int beerId) {
    model.addAttribute("avg_rating", ratingService.getAverageRatingOfBeer(beerId));
    model.addAttribute("getBeer", beerService.getBeerById(beerId));
    return "get-beer";
  }

  @GetMapping(path = "beers/{beerId}/addRating")
  public String addRatingToBeer(Model model,
                                @Valid @PathVariable(name = "beerId") int beerId) {
    Beer beer = beerService.getBeerById(beerId);
    model.addAttribute("beer", beer);
    model.addAttribute("rating", new Rating());
    return "create-rating";
  }

  @PostMapping(path = "beers/{beerId}/addRating")
  public String addRatingToBeer(Model model, Principal principal,
                                @Valid @PathVariable(name = "beerId") int beerId,
                                @Valid @ModelAttribute("beer") Beer beer,
                                @Valid @ModelAttribute("rating") Rating rating,
                                BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "create-rating";
    }
    String username = principal.getName();
    User user = userService.getUserByUsername(username);
    beer = beerService.getBeerById(beerId);
    rating.setBeer(beer);
    rating.setUser(user);
    ratingService.addRatingToBeer(beer.getId(), user.getId(), rating.getRatingValue());
    model.addAttribute("beers", beerService.getAllBeers());
    return "redirect:/beers/{beerId}";
  }
}
