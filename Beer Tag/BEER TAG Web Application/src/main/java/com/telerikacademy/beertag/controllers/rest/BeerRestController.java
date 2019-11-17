package com.telerikacademy.beertag.controllers.rest;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.services.contracts.BeerService;
import com.telerikacademy.beertag.services.contracts.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(value = "Beer Tag Application")
@RestController
@RequestMapping(path = "api/v1/beers")
public class BeerRestController {

  private BeerService beerService;
  private TagService tagService;

  @Autowired
  public BeerRestController(BeerService beerService, TagService tagService) {
    this.beerService = beerService;
    this.tagService = tagService;
  }

  @ApiOperation(value = "View a list of available beers", response = List.class)
  @GetMapping
  public List<Beer> getAll() {
    return beerService.getAllBeers();
  }

  @ApiOperation(value = "View beer by id", response = Beer.class)
  @GetMapping(path = "{id}")
  public Beer getById(@PathVariable(name = "id") @Valid int beerId) {
    return beerService.getBeerById(beerId);
  }

  @ApiOperation(value = "View filtered by ABV beers list", response = List.class)
  @GetMapping(path = "/byABV")
  public List<Beer> getByABV(@RequestParam double abv) {
    return beerService.getByABV(abv);
  }

  @ApiOperation(value = "View filtered by ABV between beers list", response = List.class)
  @GetMapping(path = "/betweenABV")
  public List<Beer> getByABVBetween(@RequestParam double minABV, @RequestParam double maxABV) {
    return beerService.getByABVBetweenMinAndMax(minABV, maxABV);
  }

  @ApiOperation(value = "Create beer", response = void.class)
  @PostMapping
  public void create(@RequestBody @Valid Beer beer) {
    beerService.addBeer(beer);
  }

  @ApiOperation(value = "Delete beer", response = void.class)
  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable(name = "id") @Valid int beerId) {
    beerService.deleteBeer(beerId);
  }

  @ApiOperation(value = "Update beer", response = void.class)
  @PutMapping(path = "{id}")
  public void update(@PathVariable(name = "id") @Valid int beerId, @RequestBody @Valid Beer beer) {
    beerService.updateBeer(beerId, beer);
  }

  @ApiOperation(value = "Filter beers", response = List.class)
  @GetMapping(path = "filtered")
  public List<Beer> filter(@RequestParam @Valid Map<String, String> filterParams) {
    return beerService.filter(filterParams);
  }

  @ApiOperation(value = "Sort beers", response = List.class)
  @GetMapping(path = "sorted")
  public List<Beer> sort(@RequestParam @Valid Map<String, String> sortParameters) {
    return beerService.sort(sortParameters);
  }

  @ApiOperation(value = "Add tag to beer", response = void.class)
  @PostMapping(path = "{id}/addTag/{tagName}")
  public void addTag(@Valid @PathVariable(name = "id") int beerId,
                     @Valid @PathVariable(name = "tagName") String tagName) {
    Beer beer = beerService.getBeerById(beerId);
    beer.addTag(tagService.getTagByName(tagName));
  }
}
