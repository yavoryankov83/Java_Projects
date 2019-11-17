package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Tag;
import com.telerikacademy.beertag.repositories.TagRepository;
import com.telerikacademy.beertag.services.contracts.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

  private TagRepository tagRepository;

  @Autowired
  public TagServiceImpl(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  @Override
  public Tag getTagByName(String tag) {
    return tagRepository.findByName(tag);
  }

  @Override
  public void saveTag(Tag tag) {
    tagRepository.save(tag);
  }
}