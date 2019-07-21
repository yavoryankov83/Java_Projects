package com.telerikacademy.workItemManagement.commands.createCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Bug;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewBugInBoard implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 8;

  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;

  public CreateNewBugInBoard(ModelsRepository modelsRepository,
                             ModelsFactory modelsFactory) {
    this.modelsRepository = modelsRepository;
    this.modelsFactory = modelsFactory;
  }

  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    String boardName = parameters[0];
    String title = parameters[1];
    String description = parameters[2];
    String priority = parameters[3];
    String severity = parameters[4];
    String status = parameters[5];
    String personName = parameters[6];
    String stepsToReproduce = parameters[7];

    return createNewBugInBoard(
            boardName,
            title,
            description,
            priority,
            severity,
            status,
            personName,
            stepsToReproduce);
  }

  private String createNewBugInBoard(
          String boardName,
          String title,
          String description,
          String priority,
          String severity,
          String status,
          String personName,
          String stepsToReproduce) {

    List<Board> allBoardsWithGivenName = modelsRepository.getAllTeams().values()
            .stream()
            .flatMap(team -> team.getBoards()
                    .stream())
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (allBoardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(String.format(BOARD_NAME_NOT_EXISTS_MESSAGE, boardName));
    }

    if (!modelsRepository.getMembersAssignedToTeam().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NAME_NOT_ASSIGN_TO_TEAM, personName));
    }

    String teamName = modelsRepository.getMembersAssignedToTeam().get(personName);

    Team team = modelsRepository.getAllTeams().get(teamName);

    List<Board> boardsWithGivenName = team.getBoards().
            stream()
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (boardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(String.format(NO_BOARDS_WITH_GIVEN_NAME_EXIST_MESSAGE, boardName));
    }

    Board board = boardsWithGivenName.get(0);
    Member memberOfBug = modelsRepository.getAllMembers().get(personName);
    Bug bug = modelsFactory.createBug(title, description, priority, severity, status, memberOfBug);

    splitStepsToReproduce(stepsToReproduce, bug);

    board.addWorkItem(bug);
    modelsRepository.addToWorkItems(bug.getId(), bug);
    modelsRepository.addToBugs(bug.getId(), bug);
    board.addToActivityHistory(
            String.format(
                    BUG_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            bug.getId(),
            bug.getTitle(),
            personName,
            boardName));
    bug.addToActivityHistory(
            String.format(
                    BUG_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            bug.getId(),
            bug.getTitle(),
            personName,
            boardName));

    memberOfBug.addToActivityHistory(
            String.format(
                    BUG_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            bug.getId(),
            bug.getTitle(),
            personName,
            boardName));

    return String.format(
            BUG_IN_BOARD_CREATED_SUCCESS_MESSAGE,
            bug.getId(),
            bug.getTitle(),
            personName,
            boardName);
  }

  private void splitStepsToReproduce(String stepsToReproduce, Bug bug) {
    String[] splitSteps = stepsToReproduce.split(",");
    for (String splitStep : splitSteps) {
      bug.addStep(splitStep);
    }
  }
}
