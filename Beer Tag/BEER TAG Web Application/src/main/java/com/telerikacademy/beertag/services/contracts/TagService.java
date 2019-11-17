package com.telerikacademy.beertag.services.contracts;

import com.telerikacademy.beertag.models.Tag;

import java.util.List;

public interface TagService {

  List<Tag> getAllTags();

  Tag getTagByName(String tag);

  void saveTag(Tag tag);
}
