package com.telerikacademy.workItemManagement.tests.commands;


import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowAllTeamBoards;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowAllTeamBoards_Tests {
    private Command testCommand;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowAllTeamBoards(testRepository);
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
    public void showAllTeamBoards_should_return_correct_message_when_no_team_bords_exist() {
        // Arrange
        String message = "No boards exist.";
        testRepository.getAllTeams().clear();
        // Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);
    }

    @Test
    public void showAllTeamBoards_should_return_correct_message_when_boards_exist() {
        // Arrange
        String message = "BoardBoard";
        Team team = new TeamImpl("Barcelona");
        Board board = new BoardImpl("BoardBoard");
        team.addBoard(board);
        testRepository.addToTeams(team.getName(), team);
        // Act
        String execute = testCommand.execute();
        //Assert
        Assert.assertEquals(message, execute);
    }
}
