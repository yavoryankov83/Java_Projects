package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.addingCommands.AddPersonToTeam;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddPeronToTeam_Tests {
  private ModelsRepository testRepository;
  private Command testCommand;
  private Team testTeam;
  private Member testPerson;

  @Before
  public void initialize() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new AddPersonToTeam(testRepository);
    testPerson = new MemberImpl("PersonName");
    testTeam = new TeamImpl("TeamName");
    testRepository.addToPeople("PersonName", testPerson);
    testRepository.addToTeams("TeamName", testTeam);
  }

  @Test
  public void execute_should_return_correct_string_message_when_member_and_team_exist() {
    //Arrange&&Act
    String execute = testCommand.execute(testPerson.getName(), testTeam.getName());

    //Assert
    Assert.assertEquals(String.format("Person %s was added to team %s.",
            testPerson.getName(),
            testTeam.getName()),
            execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_member_not__exist() {
    //Arrange
    //Act
    String execute = testCommand.execute("Person Name", testTeam.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_team_not__exist() {
    //Arrange
    //Act
    String execute = testCommand.execute(testPerson.getName(), "Team Name");
  }

  @Test
  public void execute_should_throw_exception_when_member_is_already_assigned() {
    //Arrange
    testRepository.addToMembersAssignedToTeam(testPerson.getName(), testTeam.getName());

    //Act
    String execute = testCommand.execute(testPerson.getName(), testTeam.getName());

    //Assert
    Assert.assertEquals(String.format("Person %s was added to team %s.", testPerson.getName(), testTeam.getName()), execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedLessArguments() {
    // Arrange
    String [] testParameters  = new String[1];
    testParameters [0] = "PersonName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String [] testParameters  = new String[3];
    testParameters [0] = "PersonName";
    testParameters [1] = "TeamName";
    testParameters [2] = "TeamName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedInvalid_team() {
    // Arrange
    String [] testParameters  = new String[2];
    testParameters [0] = "PersonName";
    testParameters [1] = "Team Name";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passed_invalid_person() {
    // Arrange
    String [] testParameters  = new String[2];
    testParameters [0] = "Person Name";
    testParameters [1] = "TeamName";

    // Act&&Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_addPerson_in_team_when_inputIsValid() {
    // Arrange
    String [] testParameters  = new String[2];
    testParameters [0] = "PersonName";
    testParameters [1] = "TeamName";

    // Act
    testCommand.execute(testParameters);

    // Assert
    Assert.assertEquals(1,testRepository.getAllTeams().get("TeamName").getMembers().size());

  }

  @Test
  public void execute_should_collect_member_activityHistory_when_inputIsValid() {
    // Arrange
    String [] testParameters  = new String[2];
    testParameters [0] = "PersonName";
    testParameters [1] = "TeamName";

    // Act
    testCommand.execute(testParameters);

    // Assert
    Assert.assertEquals("Person PersonName was added to team TeamName.",
            testPerson.getActivityHistory().get(0));
  }

  @Test
  public void execute_should_return_correct_String_when_person_is_already_added_to_team() {
    //Arrange
    testRepository.addToPeople(testPerson.getName(), testPerson);
    testRepository.addToTeams(testTeam.getName(), testTeam);
    testRepository.addToMembers(testPerson.getName(), testPerson);

    //Act
    String execute = testCommand.execute(testPerson.getName(), testTeam.getName());

    //Assert
    Assert.assertEquals(String.format("Person %s already added to team.", testPerson.getName()), execute);
  }
}
