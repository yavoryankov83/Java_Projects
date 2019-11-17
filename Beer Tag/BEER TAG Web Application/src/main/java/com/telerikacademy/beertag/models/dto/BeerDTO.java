package com.telerikacademy.beertag.models.dto;

public class BeerDTO {
  private String name;
  private String abv;
  private String description;
  private String photo;
  private String style;
  private String brewery;
  private String originCountry;

  public BeerDTO() {

  }

  public BeerDTO(String name,
                 String abv,
                 String description,
                 String photo,
                 String style,
                 String brewery,
                 String originCountry) {
    this();
    this.name = name;
    this.abv = abv;
    this.description = description;
    this.photo = photo;
    this.style = style;
    this.brewery = brewery;
    this.originCountry = originCountry;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbv() {
    return abv;
  }

  public void setAbv(String abv) {
    this.abv = abv;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getBrewery() {
    return brewery;
  }

  public void setBrewery(String brewery) {
    this.brewery = brewery;
  }

  public String getOriginCountry() {
    return originCountry;
  }

  public void setOriginCountry(String originCountry) {
    this.originCountry = originCountry;
  }
}
