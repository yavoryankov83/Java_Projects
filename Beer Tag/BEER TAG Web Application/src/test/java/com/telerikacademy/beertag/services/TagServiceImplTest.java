package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Tag;
import com.telerikacademy.beertag.repositories.TagRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TagServiceImplTest {
  @Mock
  TagRepository tagRepository;
  @InjectMocks
  TagServiceImpl tagServiceImpl;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Tag tag = new Tag("tag");
    List<Tag> list = new ArrayList<>();
    list.add(tag);
    when(tagRepository.findAll()).thenReturn(list);
  }

  @Test
  public void get_all_tags_should_return_list_of_tags() {
    //Act
    List<Tag> result = tagServiceImpl.getAllTags();
    //Assert
    Assert.assertEquals(1, result.size());
  }

  @Test
  public void return_tags_with_given_name_when_data_is_valid() {
    Tag tag = new Tag("tag");
    when(tagRepository.findByName("tag")).thenReturn(tag);
    //Act
    Tag result = tagServiceImpl.getTagByName("tag");
    //Assert
    Assert.assertEquals("tag", result.getName());
  }

  @Test
  public void should_save_new_tag() {
    //Arrange
    Tag tag = new Tag("nice");
    //Act
    tagServiceImpl.saveTag(tag);
    //Assert
    Assert.assertEquals(1, tagServiceImpl.getAllTags().size());
  }
}