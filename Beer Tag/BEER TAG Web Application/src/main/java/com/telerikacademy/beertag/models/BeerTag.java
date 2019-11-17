package com.telerikacademy.beertag.models;

import javax.persistence.*;

@Entity
@Table(name = "beers_tags")
public class BeerTag {

  @EmbeddedId
  private BeerTagId id;

  @ManyToOne
  @MapsId("beer_id")
  private Beer beer;

  @ManyToOne
  @MapsId("tag_id")
  private Tag tag;

  BeerTag() {
  }

  BeerTag(Beer beer, Tag tag) {
    this.id = new BeerTagId(beer.getId(), tag.getId());
    this.beer = beer;
    this.tag = tag;
  }

  public BeerTagId getId() {
    return id;
  }

  public void setId(BeerTagId id) {
    this.id = id;
  }

  public Beer getBeer() {
    return beer;
  }

  public void setBeer(Beer beer) {
    this.beer = beer;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }
}