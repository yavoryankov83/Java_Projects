package com.telerikacademy.beertag.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BeerTagId implements Serializable {

  @Column(name = "beer_id")
  private Integer beerId;

  @Column(name = "tag_id")
  private Integer tagId;

  public BeerTagId() {
  }

  public BeerTagId(Integer beerId, Integer tagId) {
    this.beerId = beerId;
    this.tagId = tagId;
  }

  public Integer getBeerId() {
    return beerId;
  }

  public Integer getTagId() {
    return tagId;
  }
}

