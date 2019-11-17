package com.telerikacademy.beertag.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "ratings")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Max(10)
  @Positive
  @Column(name = "value")
  private int ratingValue;

  @ManyToOne
  @JoinColumn(name = "beer_id")
  private Beer beer;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Rating() {
  }

  public Rating(int ratingValue, Beer beer, User user) {
    this();
    this.ratingValue = ratingValue;
    setBeer(beer);
    setUser(user);
  }

  public int getRatingValue() {
    return ratingValue;
  }

  public void setRatingValue(int ratingValue) {
    this.ratingValue = ratingValue;
  }

  public Beer getBeer() {
    return beer;
  }

  public User getUser() {
    return user;
  }

  public void setBeer(Beer beer) {
    this.beer = beer;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
