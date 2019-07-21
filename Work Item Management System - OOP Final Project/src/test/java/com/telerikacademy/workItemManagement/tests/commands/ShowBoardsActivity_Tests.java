package com.telerikacademy.workItemManagement.tests.commands;


import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.showCommands.ShowBoardsActivity;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowBoardsActivity_Tests {
    private Command testCommand;
    private Board testBoard;
    private ModelsRepository testRepository;

    @Before
    public void before() {
        testRepository = new ModelsRepositoryImpl();
        testCommand = new ShowBoardsActivity(testRepository);
        testBoard = new BoardImpl("BoardName");
        Team testTeam = new TeamImpl("TeamName");
        testRepository.addToTeams("TeamName",testTeam);
        testTeam.addBoard(testBoard);
        testBoard.addToActivityHistory("HistoryMessage");
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
        testParameters [0] = "BoardName";
        testParameters [1] = "BoardName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_board() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "Board Name";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_showBoardsActivity_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "BoardName";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1,
                testRepository.getAllTeams().get("TeamName").getBoards().get(0).getActivityHistory().size());
    }
}
