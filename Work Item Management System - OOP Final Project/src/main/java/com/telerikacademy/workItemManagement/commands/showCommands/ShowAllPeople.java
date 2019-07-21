package com.telerikacademy.workItemManagement.commands.showCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;

import java.util.Collection;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS_MESSAGE;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.NO_PEOPLE_EXISTS_MESSAGE;

public class ShowAllPeople implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;

  private ModelsRepository modelsRepository;

  public ShowAllPeople(ModelsRepository modelsRepository) {
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

    return showAllPeople();
  }

  private String showAllPeople() {
    if (modelsRepository.getAllPeople().isEmpty()) {
      return NO_PEOPLE_EXISTS_MESSAGE;
    }

    Collection<Member> allPeople = modelsRepository.getAllPeople().values();

    StringBuilder builder = new StringBuilder();
    allPeople.forEach(person -> builder.append(String.format("%s, ", person.getName())));

    return builder.toString().substring(0, builder.length() - 2);
  }
}
