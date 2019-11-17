package com.telerikacademy.beertag.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "origin_countries")
public class OriginCountry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;


  @Column(name = "name", nullable = false, unique = true)
  @NotNull
  @NotEmpty
  private String name;

  public OriginCountry() {
  }

  public OriginCountry(String name) {
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
}
