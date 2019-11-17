package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityBadRequestException;
import com.telerikacademy.beertag.exceptions.EntityBeerExistException;
import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.models.dto.BeerDTO;
import com.telerikacademy.beertag.repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class BeerServiceImplTest {
  @Mock
  BeerRepository beerRepository;
  @Mock
  StyleRepository styleRepository;
  @Mock
  OriginCountryRepository originCountryRepository;
  @Mock
  BreweryRepository breweryRepository;
  @Mock
  RatingRepository ratingRepository;
  @Mock
  BeerTagRepository beerTagRepository;
  @Mock
  StatusRepository statusRepository;
  @InjectMocks
  BeerServiceImpl beerServiceImpl;

  private Beer beer;
  private BeerDTO beerDTO;
  private User user;
  private Style style;
  private OriginCountry country;
  private Brewery brewery;
  private Rating rating;
  private List<Beer> beerList;
  private List<Style> styleList;
  private List<OriginCountry> countryList;
  private List<Brewery> breweryList;
  private List<Rating> ratingList;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    beer = Mockito.mock(Beer.class);
    beerDTO = Mockito.mock(BeerDTO.class);
    user = Mockito.mock(User.class);
    style = Mockito.mock(Style.class);
    country = Mockito.mock(OriginCountry.class);
    brewery = Mockito.mock(Brewery.class);
    rating = Mockito.mock(Rating.class);
    beerList = new ArrayList<>();
    beerList.add(beer);
    styleList = new ArrayList<>();
    styleList.add(style);
    countryList = new ArrayList<>();
    countryList.add(country);
    breweryList = new ArrayList<>();
    breweryList.add(brewery);
    ratingList = new ArrayList<>();
    ratingList.add(rating);
    when(beerRepository.findAll()).thenReturn(beerList);
    when(styleRepository.findAll()).thenReturn(styleList);
    when(originCountryRepository.findAll()).thenReturn(countryList);
    when(breweryRepository.findAll()).thenReturn(breweryList);
  }

  @Test(expected = EntityNotFoundException.class)
  public void should_throw_exception_when_beer_not_exists() {
    //Arrange
    when(beerRepository.getById(anyInt())).thenReturn(null);
    //Act
    Beer result = beerServiceImpl.getBeerById(1);
  }

  @Test
  public void get_all_beers_should_return_list_of_beers() {
    //Act
    List<Beer> result = beerServiceImpl.getAllBeers();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_all_styles_should_return_list_of_styles() {
    //Act
    List<Style> result = beerServiceImpl.getAllStyles();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_style_by_name_should_get_style_with_same_name() {
    //Arrange
    when(styleRepository.getStyleByName(anyString())).thenReturn(style);
    //Act
    Style result = beerServiceImpl.getStyleByName("style");
    //Assert
    Assert.assertEquals(style.getName(), result.getName());
  }

  @Test
  public void get_all_countries_should_return_list_of_countries() {
    //Act
    List<OriginCountry> result = beerServiceImpl.getAllCountries();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_country_by_name_should_get_country_with_same_name() {
    //Arrange
    when(originCountryRepository.getCountriesByName(anyString())).thenReturn(country);
    //Act
    OriginCountry result = beerServiceImpl.getCountriesByName("country");
    //Assert
    Assert.assertEquals(country.getName(), result.getName());
  }

  @Test
  public void get_all_breweries_should_return_list_of_breweries() {
    //Act
    List<Brewery> result = beerServiceImpl.getAllBreweries();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void get_brewery_by_name_should_get_brewery_with_same_name() {
    //Arrange
    when(breweryRepository.getBreweryByName(anyString())).thenReturn(brewery);
    //Act
    Brewery result = beerServiceImpl.getBreweryByName("brewery");
    //Assert
    Assert.assertEquals(brewery.getName(), result.getName());
  }

  @Test
  public void get_beer_by_id_when_data_valid() {
    //Arrange
    when(beerRepository.getById(anyInt())).thenReturn(beer);
    //Act
    Beer result = beerServiceImpl.getBeerById(1);
    //Assert
    Assert.assertEquals(beer, result);
  }

  @Test
  public void get_beer_by_name_when_data_valid() {
    //Arrange
    when(beerRepository.findByName(anyString())).thenReturn(Optional.ofNullable(beer));
    //Act
    Beer result = beerServiceImpl.getBeerByName("name");
    //Assert
    Assert.assertEquals(beer, result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void get_beer_by_name_when_data_invalid() {
    //Arrange
    when(beerRepository.findByName(anyString())).thenReturn(Optional.empty());
    //Act
    Beer result = beerServiceImpl.getBeerByName("name");
  }

  @Test
  public void get_average_rating_of_beer_when_data_valid() {
    //Arrange
    when(ratingRepository.getAverageRatingOfBeer(anyInt())).thenReturn(Double.valueOf(10));
    //Act
    Double result = beerServiceImpl.getAVG(1);
    //Assert
    Assert.assertEquals(Double.valueOf(10), result);
  }

  @Test
  public void should_return_zero_when_AVG_is_null() {
    //Arrange
    when(ratingRepository.getAverageRatingOfBeer(anyInt())).thenReturn(null);
    //Act
    Double result = beerServiceImpl.getAVG(1);
    //Assert
    Assert.assertEquals(Double.valueOf(0), result);
  }

  @Test
  public void get_list_of_beers_with_given_ABV() {
    //Arrange
    when(beerRepository.getByAbv(anyDouble())).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.getByABV(4.5);
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void get_list_of_beers_with_ABV_between() {
    //Arrange
    when(beerRepository.getByAbvBetween(anyDouble(), anyDouble())).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.getByABVBetweenMinAndMax(2.5, 4.5);
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test(expected = EntityConflictException.class)
  public void should_throw_exception_when_beer_not_existing() {
    //Arrange
    when(beerRepository.existsById(1)).thenReturn(false);
    when(beerRepository.getById(1)).thenReturn(null);
    //Act
    BeerDTO result = beerServiceImpl.convertBeerToDTO(1);
  }

  @Test
  public void should_convert_beer_to_DTO() {
    //Arrange
    Beer beer = new Beer("name",
            5.5, "", "", brewery, style, country);
    when(beerRepository.existsById(1)).thenReturn(true);
    when(beerRepository.getById(1)).thenReturn(beer);
    //Act
    BeerDTO result = beerServiceImpl.convertBeerToDTO(1);
    //Assert
    Assert.assertEquals(beer.getBrewery().getName(), result.getBrewery());
  }

  @Test(expected = EntityBeerExistException.class)
  public void should_throw_exception_when_beer_exists() {
    //Arrange
    when(beerRepository.existsByName(beer.getName())).thenReturn(true);
    //Act
    beerServiceImpl.addBeer(beer);
  }

  @Test
  public void should_add_beer_when_data_valid() {
    //Arrange
    when(beerRepository.existsByName(beer.getName())).thenReturn(false);
    //Act
    beerServiceImpl.addBeer(beer);
    //Assert
    Assert.assertEquals(1, beerList.size());
  }

  @Test
  public void should_add_beer_by_DTO_when_data_valid() {
    //Arrange
    BeerDTO beerDTO = new BeerDTO("name", "5.5", "", "",
            "brewery", "style", "country");
    Beer beer = new Beer();
    when(beerRepository.existsByName(beerDTO.getName())).thenReturn(false);
    when(styleRepository.getStyleByName(anyString())).thenReturn(style);
    when(originCountryRepository.getCountriesByName(anyString())).thenReturn(country);
    when(breweryRepository.getBreweryByName(anyString())).thenReturn(brewery);
    //Act
    beerServiceImpl.addBeerByDTO(beerDTO, beer);
    //Assert
    Assert.assertEquals(beerDTO.getName(), beer.getName());
  }

  @Test(expected = EntityBeerExistException.class)
  public void should_add_beer_by_DTO_when_data_invalid() {
    //Arrange
    BeerDTO beerDTO = new BeerDTO("name", "5.5", "", "",
            "brewery", "style", "country");
    Beer beer = new Beer();
    when(beerRepository.existsByName(beerDTO.getName())).thenReturn(true);
    when(styleRepository.getStyleByName(anyString())).thenReturn(style);
    when(originCountryRepository.getCountriesByName(anyString())).thenReturn(country);
    when(breweryRepository.getBreweryByName(anyString())).thenReturn(brewery);
    //Act
    beerServiceImpl.addBeerByDTO(beerDTO, beer);
  }

  @Test
  public void should_update_beer_when_data_valid() {
    //Arrange
    Beer beer = new Beer("name",
            5.5, "", "", brewery, style, country);
    when(beerRepository.getById(1)).thenReturn(beer);
    beerList.add(beer);
    //Act
    beerServiceImpl.updateBeer(1, beer);
    //Assert
    Assert.assertEquals(2, beerRepository.findAll().size());
  }

  @Test
  public void should_update_beer_by_DTO_when_data_valid() {
    //Arrange
    BeerDTO beerDTO = new BeerDTO("name", "5.5", "", "",
            "brewery", "style", "country");
    when(beerRepository.getById(1)).thenReturn(beer);
    when(beerRepository.existsById(1)).thenReturn(true);
    when(styleRepository.getStyleByName("style")).thenReturn(style);
    when(originCountryRepository.getCountriesByName("country")).thenReturn(country);
    when(breweryRepository.getBreweryByName("brewery")).thenReturn(brewery);
    //Act
    beerServiceImpl.updateBeerByDTO(1, beerDTO);
    //Assert
    Assert.assertEquals(1, beerRepository.findAll().size());
  }

  @Test(expected = EntityConflictException.class)
  public void should_update_beer_by_DTO_when_data_invalid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    when(beerRepository.existsById(1)).thenReturn(false);
    when(styleRepository.getStyleByName("style")).thenReturn(style);
    when(originCountryRepository.getCountriesByName("country")).thenReturn(country);
    when(breweryRepository.getBreweryByName("brewery")).thenReturn(brewery);
    //Act
    beerServiceImpl.updateBeerByDTO(1, beerDTO);
  }

  @Test
  public void should_delete_beer_when_data_valid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    doNothing().when(ratingRepository).deleteByBeerId(1);
    doNothing().when(statusRepository).deleteByBeerId(1);
    beerList.remove(beer);
    //Act
    beerServiceImpl.deleteBeer(1);
    //Assert
    Assert.assertEquals(0, beerRepository.findAll().size());
  }

  @Test
  public void should_sort_name_asc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer);
    beerList.add(beer1);
    when(beer.getName()).thenReturn("astika");
    when(beer1.getName()).thenReturn("zagorka");
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "name_asc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_sort_name_desc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer1);
    beerList.add(beer);
    when(beer.getName()).thenReturn("astika");
    when(beer1.getName()).thenReturn("zagorka");
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "name_desc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_sort_abv_asc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer);
    beerList.add(beer1);
    when(beer.getAbv()).thenReturn(2.2);
    when(beer1.getAbv()).thenReturn(5.5);
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "abv_asc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_sort_abv_desc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer1);
    beerList.add(beer);
    when(beer.getAbv()).thenReturn(2.2);
    when(beer1.getAbv()).thenReturn(5.5);
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "abv_desc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_sort_rating_asc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer);
    beerList.add(beer1);
    when(beer.getAverageRating()).thenReturn(2.2);
    when(beer1.getAverageRating()).thenReturn(5.5);
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "rating_asc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_sort_rating_desc_beers_when_data_valid() {
    //Arrange
    Beer beer1 = Mockito.mock(Beer.class);
    beerList.clear();
    beerList.add(beer1);
    beerList.add(beer);
    when(beer.getAverageRating()).thenReturn(2.2);
    when(beer1.getAverageRating()).thenReturn(5.5);
    //Act
    List<Beer> result = beerServiceImpl.sort(new HashMap<String, String>() {{
      put("sort", "rating_desc");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_filter_by_tag_when_input_tag_parameter() {
    //Arrange
    List<Beer> beerList = new ArrayList<>();
    Beer beer1 = new Beer("Astika", 5.5, "", "",
            new Brewery("Astika AD"), new Style("ALE"), new OriginCountry("BULGARIA"));
    beer1.addTag(new Tag("nice"));
    Beer beer2 = new Beer("Zagorka", 3.2, "", "",
            new Brewery("Zagorka AD"), new Style("DARK"), new OriginCountry("GERMANY"));
    beer2.addTag(new Tag("cool"));
    beerList.add(beer1);
    beerList.add(beer2);
    Map<String, String[]> paramMap = new HashMap<>();
    String[] params = new String[1];
    params[0] = "cool";
    paramMap.put("tag", params);
    when(beerRepository.findAll()).thenReturn(beerList);
    //Act
    List<Beer> filteredBeers = beerServiceImpl.filterr(beerList, paramMap);
    //Assert
    Assert.assertEquals(1, filteredBeers.size());
  }

  @Test
  public void should_filter_by_style_when_input_style_parameter() {
    //Arrange
    List<Beer> beerList = new ArrayList<>();
    Beer beer1 = new Beer("Astika", 5.5, "", "",
            new Brewery("Astika AD"), new Style("ALE"), new OriginCountry("BULGARIA"));
    Beer beer2 = new Beer("Zagorka", 3.2, "", "",
            new Brewery("Zagorka AD"), new Style("DARK"), new OriginCountry("GERMANY"));
    beerList.add(beer1);
    beerList.add(beer2);
    Map<String, String[]> paramMap = new HashMap<>();
    String[] params = new String[1];
    params[0] = "ALE";
    paramMap.put("style", params);
    when(beerRepository.findAll()).thenReturn(beerList);
    //Act
    List<Beer> filteredBeers = beerServiceImpl.filterr(beerList, paramMap);
    //Assert
    Assert.assertEquals(1, filteredBeers.size());
  }

  @Test
  public void should_filter_by_country_when_input_country_parameter() {
    //Arrange
    List<Beer> beerList = new ArrayList<>();
    Beer beer1 = new Beer("Astika", 5.5, "", "",
            new Brewery("Astika AD"), new Style("ALE"), new OriginCountry("BULGARIA"));
    Beer beer2 = new Beer("Zagorka", 3.2, "", "",
            new Brewery("Zagorka AD"), new Style("DARK"), new OriginCountry("GERMANY"));
    beerList.add(beer1);
    beerList.add(beer2);
    Map<String, String[]> paramMap = new HashMap<>();
    String[] params = new String[1];
    params[0] = "GERMANY";
    paramMap.put("country", params);
    when(beerRepository.findAll()).thenReturn(beerList);
    //Act
    List<Beer> filteredBeers = beerServiceImpl.filterr(beerList, paramMap);
    //Assert
    Assert.assertEquals(1, filteredBeers.size());
  }

  @Test
  public void should_filter_by_rating_when_input_rating_parameter() {
    //Arrange
    List<Beer> beerList = new ArrayList<>();
    Beer beer1 = new Beer("Astika", 5.5, "", "",
            new Brewery("Astika AD"), new Style("ALE"), new OriginCountry("BULGARIA"));
    Beer beer2 = new Beer("Zagorka", 3.2, "", "",
            new Brewery("Zagorka AD"), new Style("DARK"), new OriginCountry("GERMANY"));
    Rating rating1 = new Rating();
    rating1.setRatingValue(5);
    beer1.addRating(rating1);
    Rating rating2 = new Rating();
    rating2.setRatingValue(10);
    beer2.addRating(rating2);
    beerList.add(beer1);
    beerList.add(beer2);
    Map<String, String[]> paramMap = new HashMap<>();
    String[] params = new String[1];
    params[0] = "10";
    paramMap.put("rating", params);
    when(beerRepository.findAll()).thenReturn(beerList);
    //Act
    List<Beer> filteredBeers = beerServiceImpl.filterr(beerList, paramMap);
    //Assert
    Assert.assertEquals(1, filteredBeers.size());
  }

  @Test
  public void should_filter_by_status_when_input_status_parameter() {
    //Arrange
    List<Beer> beerList = new ArrayList<>();
    Beer beer1 = new Beer("Astika", 5.5, "", "",
            new Brewery("Astika AD"), new Style("ALE"), new OriginCountry("BULGARIA"));
    Beer beer2 = new Beer("Zagorka", 3.2, "", "",
            new Brewery("Zagorka AD"), new Style("DARK"), new OriginCountry("GERMANY"));
    Status status1 = new Status();
    status1.setStatusValue(new StatusValue("want"));
    beer1.addStatus(status1);
    Status status2 = new Status();
    status2.setStatusValue(new StatusValue("drank"));
    beer2.addStatus(status2);
    beerList.add(beer1);
    beerList.add(beer2);
    Map<String, String[]> paramMap = new HashMap<>();
    String[] params = new String[1];
    params[0] = "want";
    paramMap.put("status", params);
    when(beerRepository.findAll()).thenReturn(beerList);
    //Act
    List<Beer> filteredBeers = beerServiceImpl.filterr(beerList, paramMap);
    //Assert
    Assert.assertEquals(1, filteredBeers.size());
  }

//    @Test
//    public void should_filter_beers_when_data_valid() {
//        //Arrange
//        Beer beer1 = Mockito.mock(Beer.class);
//        beerList.clear();
//        beerList.add(beer1);
//        beerList.add(beer);
//        Set<Tag>beerTags = new HashSet<>();
//        beerTags.add(new Tag("tag"));  //
//        when(beer.getTags()).thenReturn(beerTags);
//        when(beer1.getAverageRating()).thenReturn(5.5);
//        //Act
//        List<Beer> result = beerServiceImpl.filterr(beerList, new HashMap<String, String>() {{
//            put("tag", new String[]{"String"});
//        }});
//        //Assert
//        Assert.assertEquals(Arrays.<Beer>asList(new Beer("name", 0d, "description", "photo", new Brewery("name"), new Style("name"), new OriginCountry("name"))), result);
//    }

  //    @Test
//    public void should_filter_by_style_beers_when_data_valid() {
//        //Arrange
//        Beer beer1 = Mockito.mock(Beer.class);
//        beerList.clear();
//        beerList.add(beer);
////        beerList.add(beer1);
//        when(beer.getStyle().getName()).thenReturn("style");
//        when(beer1.getStyle().getName()).thenReturn("other");
////        String[] stylesArr = {"style"};
////        String filter = "style"
////        Map<String, String[]>filterParameters =  new HashMap<String, String[]>();
//        String[] stylesArr = {"style"};
//                //Act
//        List<Beer> result = beerServiceImpl.filterr(beerList, new HashMap<String, String[]>() {{
//            put("style", stylesArr);
//        }});
//        //Assert
//        Assert.assertEquals(beerList, result);
//    }
  @Test
  public void should_filter_by_tag_list_of_beers() {
    //Arrange
    when(beerRepository.getByTag(anyString())).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("tag", "tagValue");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_filter_by_style_list_of_beers() {
    //Arrange
    when(beerRepository.getByStyle(anyString())).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("style", "styleValue");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_filter_by_country_list_of_beers() {
    //Arrange
    when(beerRepository.getByOriginCountry(anyString())).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("country", "countryValue");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_filter_by_rating_list_of_beers() {
    //Arrange
    when(beerRepository.getByRating(Integer.parseInt("2"))).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("rating", "2");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test
  public void should_filter_by_status_list_of_beers() {
    //Arrange
    when(beerRepository.getByStatusValue(Integer.parseInt("1"))).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("status", "1");
    }});
    //Assert
    Assert.assertEquals(beerList, result);
  }

  @Test(expected = EntityBadRequestException.class)
  public void should_filter_list_of_beers() {
    //Arrange
    when(beerRepository.getByTag(anyString())).thenReturn(beerList);
    when(beerRepository.getByStyle(anyString())).thenReturn(beerList);
    when(beerRepository.getByOriginCountry(anyString())).thenReturn(beerList);
    when(beerRepository.getByRating(Integer.parseInt("1"))).thenReturn(beerList);
    when(beerRepository.getByStatusValue(Integer.parseInt("1"))).thenReturn(beerList);
    //Act
    List<Beer> result = beerServiceImpl.filter(new HashMap<String, String>() {{
      put("String", "String");
    }});
  }

  @Test
  public void get_ratings_of_beer_by_user_when_data_valid() {
    //Arrange
    when(beerRepository.getById(1)).thenReturn(beer);
    when(user.getUsername()).thenReturn("username");
    //Act
    List<Rating> result = beerServiceImpl.getRatingsOfBeerByUser(1, user.getUsername());
    //Assert
    Assert.assertEquals(1, ratingList.size());
  }
}

