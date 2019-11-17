package com.telerikacademy.beertag.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "beers")
public class Beer {

  private static final String NAME_LENGTH_MESSAGE =
          "Beer name should be between 3 and 50 symbols.";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false)
  @Size(min = 3, max = 50, message = NAME_LENGTH_MESSAGE)
  private String name;

  @Column(name = "abv", nullable = false)
  @PositiveOrZero
  private double abv;

  @Column(name = "description")
  private String description;

  @Column(name = "photo")
  private String photo;

  @ManyToOne
  @JoinColumn(name = "brewery_id")
  private Brewery brewery;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private OriginCountry originCountry;

  @ManyToOne
  @JoinColumn(name = "style_id")
  private Style style;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "beer")
  @JsonIgnore
  private List<Rating> ratings = new ArrayList<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "beer")
  @JsonIgnore
  private Set<BeerTag> tags = new HashSet<>();

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "beer")
  @JsonIgnore
  private List<Status> statuses = new ArrayList<>();

  public Beer() {
  }

  public Beer(String name, double abv, String description, String photo,
              Brewery brewery, Style style, OriginCountry originCountry) {
    this();
    this.name = name;
    this.abv = abv;
    this.description = description;
    this.photo = photo;
    this.brewery = brewery;
    this.style = style;
    this.originCountry = originCountry;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getAbv() {
    return abv;
  }

  public void setAbv(double abv) {
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

  public Brewery getBrewery() {
    return brewery;
  }

  public void setBrewery(Brewery brewery) {
    this.brewery = brewery;
  }

  public OriginCountry getOriginCountry() {
    return originCountry;
  }

  public void setOriginCountry(OriginCountry originCountry) {
    this.originCountry = originCountry;
  }

  public Style getStyle() {
    return style;
  }

  public void setStyle(Style style) {
    this.style = style;
  }

  public Set<BeerTag> getTags() {
    return tags;
  }

  public void setTags(Set<BeerTag> tags) {
    this.tags = tags;
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) {
    this.ratings = ratings;
  }

  public List<Status> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<Status> statuses) {
    this.statuses = statuses;
  }

  public BeerTag addTag(Tag tag) {
    BeerTag beerTag = new BeerTag(this, tag);
    tags.add(beerTag);
    tag.getBeers().add(beerTag);
    return beerTag;
  }

  public void removeTag(Tag tag) {
    for (Iterator<BeerTag> iterator = tags.iterator();
         iterator.hasNext(); ) {
      BeerTag beerTag = iterator.next();

      if (beerTag.getBeer().equals(this) &&
              beerTag.getTag().equals(tag)) {
        iterator.remove();
        beerTag.getTag().getBeers().remove(beerTag);
        beerTag.setBeer(null);
        beerTag.setTag(null);
      }
    }
  }

  public void addStatus(Status status) {
    statuses.add(status);
  }

  public void addRating(Rating rating) {
    ratings.add(rating);
  }

  public void deleteStatus(Status status) {
    statuses.remove(status);
  }

  public void deleteRating(Rating rating) {
    ratings.remove(rating);
  }

  public double getAverageRating() {
    List<Rating> ratings = getRatings();
    double sum = 0.0;
    for (Rating rating : ratings) {
      sum += rating.getRatingValue();
    }
    if (sum != 0.0) {
      return sum / ratings.size();
    }
    return sum;
  }
}
