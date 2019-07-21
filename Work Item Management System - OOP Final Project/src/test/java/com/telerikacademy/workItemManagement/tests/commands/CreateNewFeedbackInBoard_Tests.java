package com.telerikacademy.workItemManagement.tests.commands;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.CreateNewFeedbackInBoard;
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

public class CreateNewFeedbackInBoard_Tests {
    private Command testCommand;
    private Team testTeam;
    private Board testBoard;
    private ModelsRepository testRepository;
    private ModelsFactory testFactory;

    @Before
    public void before() {
        testFactory = new ModelsFactoryImpl();
        testRepository = new ModelsRepositoryImpl();
        testCommand = new CreateNewFeedbackInBoard(testRepository, testFactory);
        testTeam = new TeamImpl("TeamName");
        testBoard = new BoardImpl("BoardName");
        testRepository.addToTeams("TeamName",testTeam);
        testTeam.addBoard(testBoard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[4];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewFeedbackName";
        testParameters [2] = "Description";
        testParameters [3] = "1";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[6];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewFeedbackName";
        testParameters [2] = "Description";
        testParameters [3] = "1";
        testParameters [4] = "Scheduled";
        testParameters [5] = "Description";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_board() {
        // Arrange
        String [] testParameters  = new String[5];
        testParameters [0] = "Board Name";
        testParameters [1] = "NewFeedbackName";
        testParameters [2] = "Description";
        testParameters [3] = "1";
        testParameters [4] = "Scheduled";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_createNewFeedback_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[5];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewFeedbackName";
        testParameters [2] = "Description";
        testParameters [3] = "1";
        testParameters [4] = "SCHEDULED";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1, testRepository.getAllWorkItems().values().size());
    }

    @Test
    public void execute_should_collect_workItem_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[5];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewFeedbackName";
        testParameters [2] = "Description";
        testParameters [3] = "1";
        testParameters [4] = "Scheduled";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(String.format("Feedback with ID %d and title NewFeedbackName was created in board BoardName.",
                testBoard.getWorkItems().get(0).getId()),
                testBoard.getWorkItems().get(0).getActivityHistory().get(0));
    }
}
