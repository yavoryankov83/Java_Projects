package com.telerikacademy.workItemManagement.commands.common;

public final class CommandConstants {
  private CommandConstants() {
  }

  // Error messages
  public static final String INVALID_NUMBER_OF_ARGUMENTS_MESSAGE =
          "Invalid number of arguments. Expected: %d, Received: %d.";
  public static final String PERSON_EXISTS_MESSAGE =
          "Person %s already created.";
  public static final String NO_PEOPLE_EXISTS_MESSAGE =
          "There are no created persons.";
  public static final String PERSON_NOT_EXISTS_MESSAGE =
          "Person %s not exists.";
  public static final String PERSON_NOT_MEMBER_OR_NOT_EXISTS_MESSAGE =
          "Person %s is not member of a team or not exists.";
  public static final String WORKITEM_ALREADY_ASSIGNED_MESSAGE =
          "Work item with ID %d already assigned.";
  public static final String WORK_ITEM_NOT_ASSIGNED_MESSAGE =
          "Work item with ID %d is not assigned";
  public static final String WORK_ITEM_NOT_ASSIGNED_TO_GIVEN_MEMBER_MESSAGE =
          "Work item with ID %d is not assigned to member %s.";
  public static final String TEAM_EXISTS_MESSAGE =
          "Team %s already created.";
  public static final String NO_TEAMS_EXIST_MESSAGE =
          "No teams exist.";
  public static final String TEAM_NOT_EXISTS_MESSAGE =
          "Team %s not exists.";
  public static final String PERSON_ALREADY_ADDED_TO_TEAM_MESSAGE =
          "Person %s already added to team.";
  public static final String NO_TEAM_MEMBERS_EXIST_MESSAGE =
          "No members exist.";
  public static final String BOARD_IN_TEAM_EXISTS_MESSAGE =
          "Board %s already created in team %s.";
  public static final String NO_TEAM_BOARDS_EXIST_MESSAGE =
          "No boards exist.";
  public static final String NO_BOARDS_WITH_GIVEN_NAME_EXIST_MESSAGE =
          "No boards with name %s exist.";
  public static final String BUG_NOT_EXIST_MESSAGE =
          "Bug with id %d not exists.";
  public static final String INVALID_PRIORITY_TYPE_MESSAGE =
          "Priority type %s is not a valid PriorityType enum.";
  public static final String INVALID_SEVERITY_TYPE_MESSAGE =
          "Severity type %s is not a valid SeverityType enum.";
  public static final String INVALID_BUG_STATUS_TYPE_MESSAGE =
          "Status type %s is not a valid BugStatusType enum.";
  public static final String STORY_NOT_EXISTS_MESSAGE =
          "Story with id %d is not exists.";
  public static final String INVALID_SIZE_TYPE_MESSAGE =
          "Size type %s is not a valid SizeType enum.";
  public static final String INVALID_STORY_STATUS_TYPE_MESSAGE =
          "Status type %s is not a valid StoryStatusType enum.";
  public static final String FEEDBACK_NOT_EXISTS_MESSAGE =
          "Feedback with id %d not exists.";
  public static final String INVALID_FEEDBACK_STATUS_TYPE_MESSAGE =
          "Status type %s is not a valid FeedbackStatusType enum.";
  public static final String WORK_ITEM_NOT_EXISTS_MESSAGE =
          "Work item with id %d not exists.";
  public static final String NOT_VALID_STATUS_TYPE_MESSAGE =
          "Status %s is not valid String.";
  public static final String WORK_ITEM_WITH_ASSIGNEE_NAME_NOT_EXISTS_MESSAGE =
          "Work item with assignee name %s not exists.";
  public static final String WORK_ITEM_WITH_STATUS_NOT_EXISTS_MESSAGE =
          "Work item with status %s not exists.";
  public static final String NO_WORK_ITEMS_EXISTS_MESSAGE =
          "No work items exist.";
  public static final String BOARD_NAME_NOT_EXISTS_MESSAGE =
          "Board with name %s not exist.";
  public static final String PERSON_NAME_NOT_ASSIGN_TO_TEAM =
          "Person with name %s not assignned to team.";
  public static final String LIST_WORK_ITEM_EMPTY_MESSAGE =
          "List of work items is empty.";
  public static final String NO_WORK_ITEMS_OF_TYPE_BUG_AND_STORY_MESSAGE =
          "Work items of type Bug and Story not exist.";
  public static final String NO_WORK_ITEMS_OF_TYPE_STORY_MESSAGE =
          "Work items of type Story not exist.";
  public static final String NO_WORK_ITEMS_OF_TYPE_FEEDBACK_MESSAGE =
          "Work items of type Feedback not exist.";
  public static final String NO_WORK_ITEMS_OF_TYPE_BUG_MESSAGE =
          "Work items of type Bug not exist.";
  public static final String NO_TEAM_ITEMS_EXIST_MESSAGE =
          "No members and boards exist in team %s.";
  public static final String FEEDBACK_STATUS_TYPE_WITH_NO_ASSIGNEE =
          "Status type %s is FeedbackStatusType and it has no assignee.";
  public static final String WORK_ITEM_ASSIGNEE_NOT_EQUAL_MEMBER =
          "Work item with assignee name %s can not be assigned to member %s";

  // Success messages
  public static final String PERSON_CREATED_SUCCESS_MESSAGE =
          "Person %s was created.";
  public static final String TEAM_CREATED_SUCCESS_MESSAGE =
          "Team %s was created.";
  public static final String PERSON_ADDED_TO_TEAM_SUCCESS_MESSAGE =
          "Person %s was added to team %s.";
  public static final String BOARD_IN_TEAM_CREATED_SUCCESS_MESSAGE =
          "Board %s was created in team %s.";
  public static final String BUG_CHANGE_PRIORITY_SUCCESS_MESSAGE =
          "Priority of bug with id %d was changed to %s.";
  public static final String BUG_CHANGE_SEVERITY_SUCCESS_MESSAGE =
          "Severity of bug with id %d was changed to %s.";
  public static final String BUG_CHANGE_STATUS_SUCCESS_MESSAGE =
          "Status of bug with id %d was changed to %s.";
  public static final String STORY_CHANGE_PRIORITY_SUCCESS_MESSAGE =
          "Priority of story with id %d was changed to %s.";
  public static final String STORY_CHANGE_SIZE_SUCCESS_MESSAGE =
          "Size of story with id %d was changed to %s.";
  public static final String STORY_CHANGE_STATUS_SUCCESS_MESSAGE =
          "Status of story with id %d was changed to %s.";
  public static final String FEEDBACK_CHANGE_RATING_SUCCESS_MESSAGE =
          "Rating of feedback with id %d was changed to %d.";
  public static final String FEEDBACK_CHANGE_STATUS_SUCCESS_MESSAGE =
          "Status of feedback with id %d was changed to %s.";
  public static final String COMMENT_CREATED_SUCCESS_MESSAGE =
          "Comment was created by %s in work item %d.";
  public static final String BUG_IN_BOARD_CREATED_SUCCESS_MESSAGE =
          "Bug with ID %d, title %s and member name %s was created in board %s.";
  public static final String STORY_IN_BOARD_CREATED_SUCCESS_MESSAGE =
          "Story with ID %d and title %s and member name %s was created in board %s.";
  public static final String FEEDBACK_IN_BOARD_CREATED_SUCCESS_MESSAGE =
          "Feedback with ID %d and title %s was created in board %s.";
  public static final String WORK_ITEM_ADDED_SUCCESS_MESSAGE =
          "Work item with ID %d added to member %s.";
  public static final String WORK_ITEM_REMOVED_SUCCESS_MESSAGE =
          "Work item with ID %d removed from member %s.";
  public static final String MEMBER_NOT_EXIST_MESSAGE = "Member with name %s not exists";
  public static final String AUTHOR_AND_WORKITEM_NOT_IN_SAME_TEAM_MESSAGE =
          "Author %s is not allowed to give comment to work item with ID %d, because they are not in same team.";
}
