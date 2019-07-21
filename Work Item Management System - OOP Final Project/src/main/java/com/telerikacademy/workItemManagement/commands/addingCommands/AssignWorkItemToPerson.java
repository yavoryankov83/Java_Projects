package com.telerikacademy.workItemManagement.commands.addingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;
import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.WORK_ITEM_ASSIGNEE_NOT_EQUAL_MEMBER;

public class  AssignWorkItemToPerson implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

  private ModelsRepository modelsRepository;

  public AssignWorkItemToPerson(
          ModelsRepository modelsRepository) {
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

    return assignWorkItemToPerson(workItemId, personName);
  }

  private String assignWorkItemToPerson(long workItemId, String personName) {
    if (!modelsRepository.getAllMembers().containsKey(personName)) {
      throw new IllegalArgumentException(String.format(PERSON_NOT_MEMBER_OR_NOT_EXISTS_MESSAGE, personName));
    }
    if (!modelsRepository.getAllWorkItems().containsKey(workItemId)) {
      throw new IllegalArgumentException(String.format(WORK_ITEM_NOT_EXISTS_MESSAGE, workItemId));
    }
    if (modelsRepository.getAllWorkItemsAlreadyAssigned().containsKey(workItemId)) {
      throw new IllegalArgumentException(String.format(WORKITEM_ALREADY_ASSIGNED_MESSAGE, workItemId));
    }

    List<Assignable> bugsAndStories = new ArrayList<>();

    if (modelsRepository.getAllBugs().containsKey(workItemId)){
      Bug bug = modelsRepository.getAllBugs().get(workItemId);
      bugsAndStories.add(bug);
    } else if (modelsRepository.getAllStories().containsKey(workItemId)){
      Story story = modelsRepository.getAllStories().get(workItemId);
      bugsAndStories.add(story);
    }

    if (!bugsAndStories.isEmpty()){
      Assignable workItem = bugsAndStories.get(0);
      String assigneeName = workItem.getAssignee().getName();

      if (!assigneeName.equals(personName)){
        throw new IllegalArgumentException(
                String.format(WORK_ITEM_ASSIGNEE_NOT_EQUAL_MEMBER,
                        assigneeName,
                        personName));
      }
    }

    WorkItem workItemToAssign = modelsRepository.getAllWorkItems().get(workItemId);

    Member member = modelsRepository.getAllMembers().get(personName);

    member.addWorkItem(workItemToAssign);
    member.addToActivityHistory(
            String.format(
                    WORK_ITEM_ADDED_SUCCESS_MESSAGE,
                    workItemId,
                    personName));
    workItemToAssign.addToActivityHistory(
            String.format(
                    WORK_ITEM_ADDED_SUCCESS_MESSAGE,
                    workItemId,
                    personName));
    modelsRepository.addToWorkItemsAlreadyAssigned(workItemId, workItemToAssign);

    return String.format(WORK_ITEM_ADDED_SUCCESS_MESSAGE, workItemId, personName);
  }
}
