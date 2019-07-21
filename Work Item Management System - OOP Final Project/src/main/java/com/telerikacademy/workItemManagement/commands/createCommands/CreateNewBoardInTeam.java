package com.telerikacademy.workItemManagement.commands.createCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class CreateNewBoardInTeam implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;
  private ModelsFactory factory;

  public CreateNewBoardInTeam(ModelsRepository modelsRepository, ModelsFactory factory) {
    this.modelsRepository = modelsRepository;
    this.factory = factory;
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
    String teamName = parameters[1];

    return createNewBoardInTeam(boardName, teamName);
  }

  private String createNewBoardInTeam(String boardName, String teamName) {
    if (!modelsRepository.getAllTeams().containsKey(teamName)) {
      throw new IllegalArgumentException(String.format(TEAM_NOT_EXISTS_MESSAGE, teamName));
    }

    Team team = modelsRepository.getAllTeams().get(teamName);
    boolean isBoardExistsInTeam = team.getBoards()
            .stream()
            .anyMatch(board -> board.getName().equals(boardName));

    if (isBoardExistsInTeam) {
      throw new IllegalArgumentException(String.format(BOARD_IN_TEAM_EXISTS_MESSAGE, boardName, teamName));
    }

    Board board = factory.createBoard(boardName);

    team.addBoard(board);
    board.addToActivityHistory(String.format(BOARD_IN_TEAM_CREATED_SUCCESS_MESSAGE, boardName, teamName));

    return String.format(BOARD_IN_TEAM_CREATED_SUCCESS_MESSAGE, boardName, teamName);
  }
}
