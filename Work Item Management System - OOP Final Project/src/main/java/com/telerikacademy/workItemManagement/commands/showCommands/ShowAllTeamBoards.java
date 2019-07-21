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
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_TEAM_BOARDS_EXIST_MESSAGE;

public class ShowAllTeamBoards implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ShowAllTeamBoards(ModelsRepository modelsRepository) {
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

    return showAllTeamBoards();
  }

  private String showAllTeamBoards() {
    Collection<Team> allTeams = modelsRepository.getAllTeams().values();

    List<Board> allBoards = allTeams
            .stream()
            .flatMap(team -> team.getBoards()
                    .stream())
            .collect(Collectors.toList());

    if (allBoards.isEmpty()) {
      return NO_TEAM_BOARDS_EXIST_MESSAGE;
    }

    StringBuilder builder = new StringBuilder();
    allBoards.forEach(board -> builder.append(String.format("%s, ", board.getName())));

    return builder.toString().substring(0, builder.length() - 2);
  }
}
