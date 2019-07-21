package com.telerikacademy.workItemManagement.commands.addingCommands;

import com.telerikacademy.workItemManagement.commands.common.Validator;
import com.telerikacademy.workItemManagement.commands.contracts.Command;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.workItemManagement.commands.common.CommandConstants.*;

public class AddCommentToWorkItem implements Command {
  private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;

  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;

  public AddCommentToWorkItem(ModelsRepository modelsRepository, ModelsFactory modelsFactory) {
    this.modelsRepository = modelsRepository;
    this.modelsFactory = modelsFactory;
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
    String authorName = parameters[1];
    String message = parameters[2];

    return addCommentToWorkItem(workItemId, authorName, message);
  }

  private String addCommentToWorkItem(long workItemId, String authorName, String message) {
    if (!modelsRepository.getAllWorkItems().containsKey(workItemId)) {
      throw new IllegalArgumentException(String.format(WORK_ITEM_NOT_EXISTS_MESSAGE, workItemId));
    }
    if (!modelsRepository.getAllMembers().containsKey(authorName)) {
      throw new IllegalArgumentException(String.format(MEMBER_NOT_EXIST_MESSAGE, authorName));
    }

    String teamName = modelsRepository.getMembersAssignedToTeam().get(authorName);
    Team team = modelsRepository.getAllTeams().get(teamName);

    List<WorkItem> workItemsToAddComment = team.getBoards()
            .stream()
            .flatMap(board -> board.getWorkItems()
                    .stream()
                    .filter(workItem -> workItem.getId() == workItemId))
            .collect(Collectors.toList());

    if (workItemsToAddComment.isEmpty()) {
      return String.format(AUTHOR_AND_WORKITEM_NOT_IN_SAME_TEAM_MESSAGE,
              authorName,
              workItemId);
    }

    WorkItem workItemToAddComment = workItemsToAddComment.get(0);

    Member member = modelsRepository.getAllMembers().get(authorName);

    Comment comment = modelsFactory.createComment(member, message);

    workItemToAddComment.addComment(comment);
    workItemToAddComment.addToActivityHistory(
            String.format(
                    COMMENT_CREATED_SUCCESS_MESSAGE,
                    authorName,
                    workItemId));
    member.addToActivityHistory(String.format(COMMENT_CREATED_SUCCESS_MESSAGE, authorName, workItemId));

    return String.format(COMMENT_CREATED_SUCCESS_MESSAGE, authorName, workItemId);
  }
}
