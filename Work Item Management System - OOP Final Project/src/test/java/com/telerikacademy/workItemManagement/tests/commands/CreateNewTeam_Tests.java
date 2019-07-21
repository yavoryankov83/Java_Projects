package com.telerikacademy.workItemManagement.tests.commands;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.CreateNewTeam;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateNewTeam_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;
    private ModelsFactory testFactory;

    @Before
    public void before() {
        testFactory = new ModelsFactoryImpl();
        testRepository = new ModelsRepositoryImpl();
        testCommand = new CreateNewTeam(testRepository, testFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[0];

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[2];

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_createNewTeam_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "Team name";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1, testRepository.getAllTeams().values().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_team_exists() {
        // Arrange
        Team team = new TeamImpl("Barcelona");
        testRepository.addToTeams(team.getName(), team);
        String teamName = "Barcelona";

        // Act & Assert
        testCommand.execute(teamName);
    }
}
