package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_BOARDS_WITH_GIVEN_NAME_EXIST_MESSAGE;

public class ShowBoardsActivity implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;

  public ShowBoardsActivity(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
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

    return showBoardsActivity(boardName);
  }

  private String showBoardsActivity(String boardName) {
    Collection<Team> allTeams = modelsRepository.getAllTeams().values();

    List<Board> allBoardsWithGivenName = allTeams
            .stream()
            .flatMap(team -> team.getBoards()
                    .stream())
            .filter(board -> board.getName().equals(boardName))
            .collect(Collectors.toList());

    if (allBoardsWithGivenName.isEmpty()) {
      throw new IllegalArgumentException(NO_BOARDS_WITH_GIVEN_NAME_EXIST_MESSAGE);
    }

    StringBuilder builder = new StringBuilder();
    allBoardsWithGivenName.forEach(board -> builder.append(board.showActivityHistory()));

    return builder.toString();
  }
}
