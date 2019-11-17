package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {
  List<Style> findAll();

  @Query(value = "select * from beer_tag.styles s\n" +
          "where s.name = :style_name", nativeQuery = true)
  Style getStyleByName(@Param(value = "style_name") String style_name);
}
