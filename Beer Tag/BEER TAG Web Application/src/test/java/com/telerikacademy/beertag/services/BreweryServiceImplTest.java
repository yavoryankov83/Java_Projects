package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityConflictException;
import com.telerikacademy.beertag.models.Brewery;
import com.telerikacademy.beertag.repositories.BreweryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BreweryServiceImplTest {
  @Mock
  BreweryRepository breweryRepository;
  @InjectMocks
  BreweryServiceImpl breweryServiceImpl;

  private Brewery brewery;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    brewery = new Brewery("name");
    List<Brewery> list = new ArrayList<>();
    list.add(brewery);
    when(breweryRepository.findAll()).thenReturn(list);
  }

  @Test(expected = EntityConflictException.class)
  public void should_throw_exception_when_brewery_exist() {
    //Arrange
    when(breweryRepository.getBreweryByName("name")).thenReturn(brewery);
    //Act
    breweryServiceImpl.addBrewery("name");
  }

  @Test
  public void should_add_brewery() {
    when(breweryRepository.getBreweryByName(anyString())).thenReturn(null);
    //Act
    breweryServiceImpl.addBrewery("name");
    //Assert
    Assert.assertEquals(1, breweryRepository.findAll().size());
  }
}