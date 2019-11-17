package com.telerikacademy.beertag.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "status_value_id")
  private StatusValue statusValue;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "beer_id")
  private Beer beer;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Status() {
  }

  public Status(Beer beer, User user, StatusValue statusValue) {
    this();
    setBeer(beer);
    setUser(user);
    setStatusValue(statusValue);
  }

  public StatusValue getStatusValue() {
    return statusValue;
  }

  public void setStatusValue(StatusValue statusValue) {
    this.statusValue = statusValue;
  }

  public Beer getBeer() {
    return beer;
  }

  public void setBeer(Beer beer) {
    this.beer = beer;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
