package com.telerikacademy.workItemManagement.tests.commands;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.commands.createCommands.CreateNewBugInBoard;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateNewBugInBoard_Tests {
    private Command testCommand;
    private Team testTeam;
    private Board testBoard;
    private Member testMember;
    private ModelsRepository testRepository;
    private ModelsFactory testFactory;

    @Before
    public void before() {
        testFactory = new ModelsFactoryImpl();
        testRepository = new ModelsRepositoryImpl();
        testCommand = new CreateNewBugInBoard(testRepository, testFactory);
        testTeam = new TeamImpl("TeamName");
        testBoard = new BoardImpl("BoardName");
        testMember = new MemberImpl("PersonName");
        testRepository.addToTeams("TeamName",testTeam);
        testRepository.addToPeople("PersonName",testMember);
        testRepository.addToMembers("PersonName",testMember);
        testRepository.addToMembersAssignedToTeam("PersonName","TeamName");
        testTeam.addBoard(testBoard);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedLessArguments() {
        // Arrange
        String [] testParameters  = new String[3];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreArguments() {
        // Arrange
        String [] testParameters  = new String[9];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";
        testParameters [8] = "Description";

        // Act & Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_board() {
        // Arrange
        String [] testParameters  = new String[8];
        testParameters [0] = "Board Name";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passed_invalid_assignee() {
        // Arrange
        String [] testParameters  = new String[8];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "Person Name";
        testParameters [7] = "StepsToReproduce";

        // Act&&Assert
        testCommand.execute(testParameters);
    }

    @Test
    public void execute_should_createNewBoard_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[8];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(1, testRepository.getAllWorkItems().values().size());
    }

    @Test
    public void execute_should_collect_workItem_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[8];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(String.format("Bug with ID %d, title NewBugName and member name PersonName was created in board BoardName.",
                testBoard.getWorkItems().get(0).getId()),
                testBoard.getWorkItems().get(0).getActivityHistory().get(0));
    }

    @Test
    public void execute_should_collect_assigne_activityHistory_when_inputIsValid() {
        // Arrange
        String [] testParameters  = new String[8];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";

        // Act
        testCommand.execute(testParameters);

        // Assert
        Assert.assertEquals(String.format("Bug with ID %d, title NewBugName and member name PersonName was created in board BoardName.",
                testBoard.getWorkItems().get(0).getId()),
                testMember.getActivityHistory().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_no_board_with_bordName_exists() {
        // Arrange
        Team team = new TeamImpl("Barcelona");
        Team team1 = new TeamImpl("RealMadrid");
        testRepository.addToTeams(team.getName(), team);
        testRepository.addToTeams(team1.getName(), team1);
        Board board = new BoardImpl("BoardName");
        Board board1 = new BoardImpl("BoardName1");
        team.addBoard(board);
        team.addBoard(board1);
        testRepository.addToMembersAssignedToTeam(
                "PersonName",
                team1.getName());

        String [] testParameters  = new String[8];
        testParameters [0] = "BoardName";
        testParameters [1] = "NewBugName";
        testParameters [2] = "Description";
        testParameters [3] = "HIGH";
        testParameters [4] = "CRITICAL";
        testParameters [5] = "ACTIVE";
        testParameters [6] = "PersonName";
        testParameters [7] = "StepsToReproduce";

        // Act&&Assert
        testCommand.execute(testParameters);
    }
}
