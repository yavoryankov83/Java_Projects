package com.telerikacademy.beertag.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
  private static final String NAME_LENGTH_MESSAGE =
          "Tag name should be between 3 and 50 symbols.";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false, unique = true)
  @Size(min = 3, max = 50, message = NAME_LENGTH_MESSAGE)
  @NotEmpty
  @NotNull
  private String name;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "tag")
  @JsonIgnore
  private Set<BeerTag> beers = new HashSet<>();

  public Tag() {
  }

  public Tag(String name) {
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

  public Set<BeerTag> getBeers() {
    return beers;
  }

  public void setBeers(Set<BeerTag> beers) {
    this.beers = beers;
  }
}
