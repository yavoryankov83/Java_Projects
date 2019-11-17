package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.BeerTag;
import com.telerikacademy.beertag.models.BeerTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerTagRepository extends JpaRepository<BeerTag, BeerTagId> {
  @Query(value = "select * from beer_tag.beers_tags bt\n" +
          "join beer_tag.beers b on b.id = bt.beer_id\n" +
          "join beer_tag.tags t on t.id = bt.tag_id\n" +
          "where t.id = :tag_id and b.id = :beer_id", nativeQuery = true)
  BeerTag findByBeerAndTag(@Param(value = "beer_id") int beer_id,
                           @Param(value = "tag_id") int tag_id);

  void deleteByBeerId(int beerId);
}
