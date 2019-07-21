package com.telerikacademy.workItemManagement.tests.core.factories;

import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.*;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class ModelsFactoryImpl_Tests {

  @Test
  public void createTeam_should_createNewTeam_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();
    Team team = factory.createTeam("Barcelona");

    // Assert
    Assert.assertEquals("Barcelona", team.getName());
  }

  @Test
  public void createBoard_should_createNewBoard_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();
    Board board = factory.createBoard("Board");

    // Assert
    Assert.assertEquals("Board", board.getName());
  }

  @Test
  public void createMember_should_createNewMember_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();
    Member member = factory.createMember("Messi");

    // Assert
    Assert.assertEquals("Messi", member.getName());
  }

  @Test
  public void createBug_should_createNewBug_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();

    Bug bug = factory.createBug("1234567890",
            "1234567890",
            "High",
            "Critical",
            "Active",
            new MemberImpl("Ivana"));

    // Assert
    Assert.assertEquals("1234567890", bug.getTitle());
    Assert.assertEquals("1234567890", bug.getDescription());
  }

  @Test
  public void createStory_should_createNewStory_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();

    Story story = factory.createStory("1234567890",
            "1234567890",
            "High",
            "Large",
            "Done",
            new MemberImpl("Ivana"));

    // Assert
    Assert.assertEquals("1234567890", story.getTitle());
    Assert.assertEquals("1234567890", story.getDescription());
  }

  @Test
  public void createFeedback_should_createNewFeedback_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();

    Feedback feedback = factory.createFeedback("1234567890",
            "1234567890",
            9,
            "Done");

    // Assert
    Assert.assertEquals("1234567890", feedback.getTitle());
    Assert.assertEquals("1234567890", feedback.getDescription());
    Assert.assertEquals(9, feedback.getRating());
  }

  @Test
  public void createComment_should_createNewComment_when_passedValidParameters() {
    // Arrange & Act
    ModelsFactory factory = new ModelsFactoryImpl();
    Member member = new MemberImpl("Ivana");

    Comment comment = factory.createComment(member,
            "comment");

    // Assert
    Assert.assertEquals("Ivana", comment.getAuthor().getName());
    Assert.assertEquals("comment", comment.getMessage());
  }
}
