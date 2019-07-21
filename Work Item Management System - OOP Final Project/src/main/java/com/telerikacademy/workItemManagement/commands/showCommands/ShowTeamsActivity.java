package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.TeamItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class ShowTeamsActivity implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

  private ModelsRepository modelsRepository;

  public ShowTeamsActivity(ModelsRepository modelsRepository) {
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

    String teamName = parameters[0];

    return showTeamsActivity(teamName);
  }

  private String showTeamsActivity(String teamName) {
    if (!modelsRepository.getAllTeams().containsKey(teamName)) {
      throw new IllegalArgumentException(String.format(TEAM_NOT_EXISTS_MESSAGE, teamName));
    }

    List<Member> allMembers = modelsRepository.getAllTeams().get(teamName).getMembers();
    List<Board> allBoards = modelsRepository.getAllTeams().get(teamName).getBoards();

    Collection<TeamItem> teamItems = new ArrayList<>();
    teamItems.addAll(allMembers);
    teamItems.addAll(allBoards);

    if (teamItems.isEmpty()) {
      return String.format(NO_TEAM_ITEMS_EXIST_MESSAGE, teamName);
    }

    StringBuilder builder = new StringBuilder();

    teamItems.forEach(teamItem -> builder.append(teamItem.showActivityHistory()));

    return builder.toString();
  }
}
