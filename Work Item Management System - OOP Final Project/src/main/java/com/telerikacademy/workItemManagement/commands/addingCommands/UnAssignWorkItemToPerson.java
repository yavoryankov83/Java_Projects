package com.telerikacademy.workItemManagement.commands.addingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;

import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.WORK_ITEM_NOT_EXISTS_MESSAGE;

public class UnAssignWorkItemToPerson implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private final ModelsRepository modelsRepository;

  public UnAssignWorkItemToPerson(ModelsRepository modelsRepository) {
    this.modelsRepository = modelsRepository;
  }

  @Override
  public String execute(String... parameters) {
    Validator.validateCommandArgumentsCount(
            parameters,
            CORRECT_NUMBER_OF_ARGUMENTS,
            String.format(
                    INVALID_NUMBER_OF_ARGUMENTS_MESSAGE,
                    CORRECT_NUMBER_OF_ARGUMENTS,
                    parameters.length));

    long workItemId = Long.parseLong(parameters[0]);
    String personName = parameters[1];

    return unAssignWorkItemToPerson(workItemId, personName);
  }

  private String unAssignWorkItemToPerson(long workItemId, String personName) {
    if (!modelsRepository.getAllMembers().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NOT_MEMBER_OR_NOT_EXISTS_MESSAGE, personName));
    }
    if (!modelsRepository.getAllWorkItems().containsKey(workItemId)) {
      throw new IllegalArgumentException(String.format(WORK_ITEM_NOT_EXISTS_MESSAGE, workItemId));
    }
    if (!modelsRepository.getAllWorkItemsAlreadyAssigned().containsKey(workItemId)) {
      throw new IllegalArgumentException(String.format(WORK_ITEM_NOT_ASSIGNED_MESSAGE, workItemId));
    }

    Member member = modelsRepository.getAllMembers().get(personName);

    List<WorkItem> workItemsToUnAssigne = member.getWorkItems()
            .stream()
            .filter(workItem -> workItem.getId() == workItemId)
            .collect(Collectors.toList());

    if (workItemsToUnAssigne.isEmpty()) {
      throw new IllegalArgumentException(String.format(WORK_ITEM_NOT_ASSIGNED_TO_GIVEN_MEMBER_MESSAGE,
              workItemId,
              personName));
    }

    WorkItem workItemToUnAssign = workItemsToUnAssigne.get(0);

    member.removeWorkItem(workItemToUnAssign);
    member.addToActivityHistory(
            String.format(
                    WORK_ITEM_REMOVED_SUCCESS_MESSAGE,
                    workItemId,
                    personName));
    workItemToUnAssign.addToActivityHistory(
            String.format(
                    WORK_ITEM_REMOVED_SUCCESS_MESSAGE,
                    workItemId,
                    personName));
    modelsRepository.removeFromWorkItemsAlreadyAssigned(workItemId, workItemToUnAssign);

    return String.format(WORK_ITEM_REMOVED_SUCCESS_MESSAGE, workItemId, personName);
  }
}
