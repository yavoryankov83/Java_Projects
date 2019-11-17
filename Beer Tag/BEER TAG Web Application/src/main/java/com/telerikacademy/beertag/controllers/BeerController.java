package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.EntityBeerExistException;
import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.models.dto.BeerDTO;
import com.telerikacademy.beertag.repositories.BeerTagRepository;
import com.telerikacademy.beertag.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "beers")
public class BeerController {
  private static final String BEER_EXIST_EXCEPTION =
          "Beer with given name already exists.";

  private BeerService beerService;
  private BreweryService breweryService;
  private TagService tagService;
  private BeerTagRepository beerTagRepository;
  private StatusService statusService;

  @Autowired
  public BeerController(BeerService beerService,
                        BreweryService breweryService,
                        TagService tagService,
                        BeerTagRepository beerTagRepository, StatusService statusService) {
    this.beerService = beerService;
    this.breweryService = breweryService;
    this.tagService = tagService;
    this.beerTagRepository = beerTagRepository;
    this.statusService = statusService;
  }

  @GetMapping
  public String getAll(Model model) {
    model.addAttribute("beersList", beerService.getAllBeers());
    model.addAttribute("queryParam", "name_asc");
    model.addAttribute("queryParam1", "name_desc");
    model.addAttribute("queryParam2", "abv_asc");
    model.addAttribute("queryParam3", "abv_desc");
    model.addAttribute("queryParam4", "rating_asc");
    model.addAttribute("queryParam5", "rating_desc");
    model.addAttribute("tags", tagService.getAllTags());
    model.addAttribute("styles", beerService.getAllStyles());
    model.addAttribute("countries", beerService.getAllCountries());

    model.addAttribute("beerDTO", new BeerDTO());
    return "get-beers";
  }

  @GetMapping(path = "{id}")
  public String beerView(Model model, @Valid @PathVariable(name = "id") int beerId, Principal principal) {
    model.addAttribute("getBeer", beerService.getBeerById(beerId));
    model.addAttribute("avgRating", beerService.getAVG(beerId));
    model.addAttribute("tags", beerService.getBeerById(beerId).getTags());
    model.addAttribute("username", principal.getName());

    List<Rating> ratingsOfBeerByUser = beerService.getRatingsOfBeerByUser(beerId, principal.getName());
    model.addAttribute("ratings", ratingsOfBeerByUser);
    if (!ratingsOfBeerByUser.isEmpty()) {
      model.addAttribute("rating", ratingsOfBeerByUser.get(0).getRatingValue());
    }

    model.addAttribute("beerDTO", beerService.convertBeerToDTO(beerId));
    model.addAttribute("breweries", beerService.getAllBreweries());
    model.addAttribute("styles", beerService.getAllStyles());
    model.addAttribute("countries", beerService.getAllCountries());

    model.addAttribute("newRating", new Rating());

    model.addAttribute("tag", new Tag());

    model.addAttribute("statusValues", statusService.getAllStatusValues());
    model.addAttribute("status", new StatusValue());
    return "get-beer";
  }

  @GetMapping(path = "create")
  public String createBeerView(Model model) {
    model.addAttribute("beerDTO", new BeerDTO());
    model.addAttribute("breweries", beerService.getAllBreweries());
    model.addAttribute("styles", beerService.getAllStyles());
    model.addAttribute("countries", beerService.getAllCountries());
    return "create-beer";
  }

  @PostMapping(path = "create")
  public String createBeer(@Valid @ModelAttribute BeerDTO beerDto, Model model) {
    Beer beer = new Beer();
    if (beerService.getBreweryByName(beerDto.getBrewery()) == null) {
      breweryService.addBrewery(beerDto.getBrewery());
    }
    beerService.addBeerByDTO(beerDto, beer);
    model.addAttribute("beers", beerService.getAllBeers());
    return "redirect:/beers";
  }

  @GetMapping(path = "update/{id}")
  public String updateBeerView(@Valid @PathVariable(name = "id") int beerId, Model model) {
    Beer beer = beerService.getBeerById(beerId);
    model.addAttribute("beer", beer);
    model.addAttribute("beerDTO", beerService.convertBeerToDTO(beerId));
    model.addAttribute("breweries", beerService.getAllBreweries());
    model.addAttribute("styles", beerService.getAllStyles());
    model.addAttribute("countries", beerService.getAllCountries());
    return "update-beer";
  }

  @PostMapping(path = "update/{id}")
  public String updateBeer(@Valid @PathVariable(name = "id") int beerId,
                           @Valid @ModelAttribute("beerDTO") BeerDTO beerDTO,
                           BindingResult bindingResult) {
    List<Beer> beerList = beerService.getAllBeers()
            .stream()
            .filter(beer -> beer.getName().equals(beerDTO.getName()) && beer.getId() != beerId)
            .collect(Collectors.toList());

    if (!beerList.isEmpty()) {
      throw new EntityBeerExistException(BEER_EXIST_EXCEPTION);
    }
    if (beerService.getBreweryByName(beerDTO.getBrewery()) == null) {
      breweryService.addBrewery(beerDTO.getBrewery());
    }
    beerService.updateBeerByDTO(beerId, beerDTO);
    return "redirect:/beers";
  }

  @GetMapping(path = "delete/{id}")
  public String deleteBeerView(@Valid @PathVariable(name = "id") Integer beerId, Model model) {
    model.addAttribute("beer", beerService.getBeerById(beerId));
    return "delete-beer";
  }

  @PostMapping(path = "delete/{id}")
  public String deleteBeer(@Valid @PathVariable(name = "id") Integer beerId,
                           @Valid @ModelAttribute Beer beer, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "delete-beer";
    }
    beerService.deleteBeer(beerId);
    return "redirect:/beers";
  }

  @GetMapping(path = "sorted/{sortParam}")
  public String getAllSortedView(Model model, @Valid @PathVariable String sortParam) {
    model.addAttribute("queryParam", "name_asc");
    model.addAttribute("queryParam1", "name_desc");
    model.addAttribute("queryParam2", "abv_asc");
    model.addAttribute("queryParam3", "abv_desc");
    model.addAttribute("queryParam4", "rating_asc");
    model.addAttribute("queryParam5", "rating_desc");
    Map<String, String> map = new HashMap<String, String>();
    map.put("sort", sortParam);
    model.addAttribute("sortedList", beerService.sort(map));
    return "get-beers-sorted";
  }

  @GetMapping(path = "filtered")
  public String getAllFilteredView(Model model, @RequestParam(name = "tag", required = false) String tag,
                                   @RequestParam(name = "style", required = false) String style,
                                   @RequestParam(name = "country", required = false) String country,
                                   @RequestParam(name = "rating", required = false) String rating,
                                   @RequestParam(name = "status", required = false) String status) {
    Map<String, String[]> map = new HashMap<>();
    String[] tagSplit = tag.split(",");
    map.put("tag", tagSplit);

    String[] styleSplit = style.split(",");
    map.put("style", styleSplit);

    String[] countrySplit = country.split(",");
    map.put("country", countrySplit);

    String[] ratingSplit = rating.split(",");
    map.put("rating", ratingSplit);

    String[] statusSplit = status.split(",");
    map.put("status", statusSplit);

    List<Beer> filteredBeers = beerService.filterr(beerService.getAllBeers(), map);
    model.addAttribute("filteredList", filteredBeers);

    return "get-beers-filtered";
  }

  @GetMapping(path = "addTag/{id}")
  public String addTagView(Model model,
                           @Valid @PathVariable(name = "id") int beerId) {
    model.addAttribute("tag", new Tag());
    model.addAttribute("beer", beerService.getBeerById(beerId));
    return "add-tag-beer";
  }

  @PostMapping(path = "addTag/{id}")
  public String addTagView(Model model,
                           @Valid @PathVariable(name = "id") int beerId,
                           @Valid @ModelAttribute("tag") Tag tag,
                           BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "add-tag-beer";
    }
    Beer beer = beerService.getBeerById(beerId);
    Tag currentTag = tagService.getTagByName(tag.getName());
    if (currentTag == null) {
      currentTag = new Tag(tag.getName());
      tagService.saveTag(currentTag);
    }
    if (beerTagRepository.findByBeerAndTag(beerId, tag.getId()) != null) {
      throw new EntityConflictException("Conflict");
    }
    BeerTag beerTag = beer.addTag(currentTag);
    beerTagRepository.save(beerTag);
    model.addAttribute("beers", beerService.getAllBeers());
    return "redirect:/beers/{id}";
  }
}