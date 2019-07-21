package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_TEAM_MEMBERS_EXIST_MESSAGE;

public class ShowAllTeamMembers implements Command {

  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ShowAllTeamMembers(ModelsRepository modelsRepository) {
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

    return showAllTeamMembers();
  }

  private String showAllTeamMembers() {
    if (modelsRepository.getAllMembers().isEmpty()) {
      return NO_TEAM_MEMBERS_EXIST_MESSAGE;
    }

    Collection<Member> allMembers = modelsRepository.getAllMembers().values();

    StringBuilder builder = new StringBuilder();
    allMembers.forEach(member -> builder.append(String.format("%s, ", member.getName())));

    return builder.toString().substring(0, builder.length() - 2);
  }
}
