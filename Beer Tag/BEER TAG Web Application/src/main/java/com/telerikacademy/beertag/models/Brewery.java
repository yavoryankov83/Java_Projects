package com.telerikacademy.beertag.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "breweries")
public class Brewery {

  private static final String NAME_LENGTH_MESSAGE =
          "Brewery name should be between 3 and 50 symbols.";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", unique = true, nullable = false)
  @Size(min = 3, max = 50, message = NAME_LENGTH_MESSAGE)
  private String name;

  public Brewery() {
  }

  public Brewery(String name) {
    this();
    this.name = name;
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

  @Override
  public String toString() {
    return this.name;
  }
}
