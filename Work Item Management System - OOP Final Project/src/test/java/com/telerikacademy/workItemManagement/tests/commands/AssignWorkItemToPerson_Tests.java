package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.addingCommands.AssignWorkItemToPerson;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Feedback;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AssignWorkItemToPerson_Tests {
  private ModelsRepository modelsRepository;
  private AssignWorkItemToPerson assignWorkItemToPerson;
  private Feedback feedback;
  private Bug bug;
  private Story story;
  private ModelsFactory modelsFactory;


  @Before
  public void initialize() {
    modelsFactory = new ModelsFactoryImpl();
    modelsRepository = new ModelsRepositoryImpl();
    assignWorkItemToPerson = new AssignWorkItemToPerson(modelsRepository);
    feedback = modelsFactory.createFeedback(
            "FeedbackFeedback",
            "DescriptionDescrip",
            4,
            "Done");
    bug = modelsFactory.createBug(
            "1234567890",
            "1234567890",
            "HIGH",
            "CRITICAL",
            "ACTIVE",
            new MemberImpl("Ivana"));
    story = modelsFactory.createStory(
            "StoryStory",
            "Description",
            "HIGH",
            "LARGE",
            "DONE",
            new MemberImpl("Ivanina"));
  }

  @Test
  public void execute_should_return_correct_string_message_when_workItem_and_person_exist() {
    //Arrange
    Member member = new MemberImpl("NeimarJ");

    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.getAllWorkItemsAlreadyAssigned().clear();
    modelsRepository.getAllBugs().clear();
    modelsRepository.getAllStories().clear();

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(feedback.getId()),
            member.getName());

    //Assert
    Assert.assertEquals(String.format("Work item with ID %d added to member %s.",
            feedback.getId(),
            member.getName()), execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_person_not_exist() {
    //Arrange
    Member member = new MemberImpl("NeimarJ");

    modelsRepository.addToWorkItems(feedback.getId(), feedback);

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(feedback.getId()),
            member.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_workitem_not_exist() {
    //Arrange
    Member member = new MemberImpl("NeimarJ");
    Feedback feedback_2 = modelsFactory.createFeedback(
            "FeedbackFeedba",
            "DescriptionDescr",
            78,
            "Done");
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.getAllWorkItems().clear();


    //Act
    assignWorkItemToPerson.execute(String.valueOf(feedback_2.getId()),
            member.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_workitem_is_assigned() {
    //Arrange
    Member member = new MemberImpl("NeimarJ");

    modelsRepository.addToPeople(member.getName(), member);
    modelsRepository.addToWorkItemsAlreadyAssigned(feedback.getId(), feedback);

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(feedback.getId()),
            member.getName());
  }

  @Test
  public void execute_should_return_correct_string_message_when_workItem_is_bug() {
    //Arrange
    Member member = new MemberImpl("Ivana");
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToWorkItems(bug.getId(), bug);
    modelsRepository.getAllWorkItemsAlreadyAssigned().clear();
    modelsRepository.addToBugs(bug.getId(), bug);
    modelsRepository.getAllStories().clear();

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(bug.getId()),
            member.getName());

    //Assert
    Assert.assertEquals(String.format("Work item with ID %d added to member %s.",
            bug.getId(),
            member.getName()), execute);
  }

  @Test
  public void execute_should_return_correct_string_message_when_workItem_is_story() {
    //Arrange
    Member member = new MemberImpl("Ivanina");
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToWorkItems(story.getId(), story);
    modelsRepository.getAllWorkItemsAlreadyAssigned().clear();
    modelsRepository.getAllBugs().clear();
    modelsRepository.addToStories(story.getId(), story);

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(story.getId()),
            member.getName());

    //Assert
    Assert.assertEquals(String.format("Work item with ID %d added to member %s.",
            story.getId(),
            member.getName()), execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_workitem_is_already_assigned() {
    //Arrange
    Member member = new MemberImpl("NeimarJ");
    Feedback feedback_2 = modelsFactory.createFeedback(
            "FeedbackFeedba",
            "DescriptionDescr",
            78,
            "Done");
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToWorkItems(feedback_2.getId(), feedback_2);
    modelsRepository.addToWorkItemsAlreadyAssigned(feedback_2.getId(), feedback_2);


    //Act
    assignWorkItemToPerson.execute(String.valueOf(feedback_2.getId()),
            member.getName());
  }

  @Test
  public void execute_should_return_correct_string_message_when_workItem_is_not_story_or_bug() {
    //Arrange
    Member member = new MemberImpl("Ivanina");
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.getAllWorkItemsAlreadyAssigned().clear();
    modelsRepository.getAllBugs().clear();
    modelsRepository.getAllStories().clear();

    //Act
    String execute = assignWorkItemToPerson.execute(String.valueOf(feedback.getId()),
            member.getName());

    //Assert
    Assert.assertEquals(String.format("Work item with ID %d added to member %s.",
            feedback.getId(),
            member.getName()), execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_return_correct_string_message_when_workItem_is_story_and_assignee_not_equal_to_person() {
    //Arrange
    Member member = new MemberImpl("Ivana");
    Story story_2 = modelsFactory.createStory(
            "StoryStory",
            "Description",
            "HIGH",
            "LARGE",
            "DONE",
            new MemberImpl("Ivanina"));
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToWorkItems(story_2.getId(), story_2);
    modelsRepository.getAllWorkItemsAlreadyAssigned().clear();
    modelsRepository.getAllBugs().clear();
    modelsRepository.addToStories(story_2.getId(), story_2);

    //Act & Assert
    String execute = assignWorkItemToPerson.execute(String.valueOf(story_2.getId()),
            member.getName());
  }
}
