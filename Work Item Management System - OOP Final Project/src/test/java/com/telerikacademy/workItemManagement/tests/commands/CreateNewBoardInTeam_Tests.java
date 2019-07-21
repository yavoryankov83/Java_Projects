package com.telerikacademy.workItemManagement.tests.commands;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.CreateNewBoardInTeam;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateNewBoardInTeam_Tests {

    private Command testCommand;
    private Team testTeam;
    private ModelsRepository testRepository;
    private ModelsFactory testFactory;

    @Before
    public void before() {
        testFactory = new ModelsFactoryImpl();
        testRepository = new ModelsRepositoryImpl();
        testCommand = new CreateNewBoardInTeam(testRepository, testFactory);
        testTeam = new TeamImpl("TeamName");
        testRepository.addToTeams("TeamName",testTeam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[1];
        testParameters [0] = "BoardName";
        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[3];
        testParameters [0] = "BoardName";
        testParameters [1] = "TeamName";
        testParameters [1] = "TeamName";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_Nonexistent_Team() {
        // Arrange
        testRepository.getAllTeams().clear();

        String [] testParameters  = new String[2];
        testParameters [0] = "BoardName";
        testParameters [1] = "Team";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_Board_Exists() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "BoardName";
        testParameters [1] = "TeamName";

        // Act
        Board board = new BoardImpl("BoardName");
        testRepository.getAllTeams().get("TeamName").addBoard(board);

        // Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_createNewBoard_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "BoardName";
        testParameters [1] = "TeamName";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertTrue( testRepository.getAllTeams().get("TeamName").getBoards()
                .stream().anyMatch(board->board.getName().contains("BoardName")));
    }

    @Test
    public void execute_should_collect_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[2];
        testParameters [0] = "BoardName";
        testParameters [1] = "TeamName";

        // Act
        testCommand.execute(testParameters);
        Board board = testRepository.getAllTeams().get("TeamName").getBoards().get(0);

        // Assert
        Assert.assertEquals("Board BoardName was created in team TeamName.", board.getActivityHistory().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_board_exist_in_team() {
        // Arrange
        Team team = new TeamImpl("Barcelona");
        Board board = new BoardImpl("BoardBoard");
        team.addBoard(board);
        testRepository.addToTeams(team.getName(), team);
        String [] testParameters  = new String[3];
        testParameters [0] = "BoardBoard";
        testParameters [1] = testTeam.getName();

        // Act&&Assert
        testCommand.execute(testParameters);
    }
}
