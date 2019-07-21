package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowTeamsActivity;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowTeamsActivity_Tests {
  private Command testCommand;
  private ModelsRepository testRepository;

  @Before
  public void before() {
    testRepository = new ModelsRepositoryImpl();
    testCommand = new ShowTeamsActivity(testRepository);

  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedLessArguments() {
    // Arrange
    String[] testParameters = new String[0];

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passedMoreArguments() {
    // Arrange
    String[] testParameters = new String[2];
    testParameters[0] = "TeamName";
    testParameters[1] = "TeamName";

    // Act & Assert
    testCommand.execute(testParameters);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throwException_when_passed_invalid_team() {
    // Arrange
    String[] testParameters = new String[1];
    testParameters[0] = "Team Name";

    // Act&&Assert
    testCommand.execute(testParameters);
  }

  @Test
  public void execute_should_return_correct_message_when_team_has_no_members_and_boards() {
    // Arrange
    Team team = new TeamImpl("Barcelona");
    testRepository.addToTeams(team.getName(), team);

    String[] testParameters = new String[1];
    testParameters[0] = "Barcelona";

    // Act
    String execute = testCommand.execute(testParameters);
    //Assert
    Assert.assertEquals(
            String.format(
                    "No members and boards exist in team %s.",
                    team.getName()),
            execute);
  }


  @Test
  public void execute_should_append_to_builder_when_team_has__members_or_boards() {
    // Arrange
    Team team = new TeamImpl("Barcelona");
    testRepository.addToTeams(team.getName(), team);
    Member member = new MemberImpl("Ivana");
    team.addMember(member);

    String[] testParameters = new String[1];
    testParameters[0] = "Barcelona";

    // Act
    String execute = testCommand.execute(testParameters);
    //Assert
    String builder = String.format("Activity history: There is no activity history for %s %s.%n%n",
            member.getType(),
            member.getName());
    Assert.assertEquals(builder, execute);
  }
}
