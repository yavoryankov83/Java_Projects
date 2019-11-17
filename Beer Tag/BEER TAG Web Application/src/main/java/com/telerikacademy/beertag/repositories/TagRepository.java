package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
  Tag findByName(String tagName);

  List<Tag> findAll();
}
