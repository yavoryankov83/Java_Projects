package com.telerikacademy.workItemManagement.tests.commands;


import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowAllTeams;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowAllTeams_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowAllTeams(testRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "TeamName";
        testParameters [1] = "TeamName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_return_correct_message_when_no_team_exists(){
        //Arrange
        testRepository.getAllTeams().clear();
        String message = "No teams exist.";
        //Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);

    }

    @Test
    public void execute_should_return_correct_output_when__teams_exist(){
        //Arrange
        Team team = new TeamImpl("Barcelona");
        testRepository.addToTeams(team.getName(), team);
        String message = "Barcelona";
        //Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);

    }
}
