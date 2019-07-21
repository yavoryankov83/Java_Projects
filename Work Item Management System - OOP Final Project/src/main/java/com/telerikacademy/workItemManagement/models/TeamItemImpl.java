package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.common.Validator;
import com.telerikacademy.workItemManagement.models.contracts.TeamItem;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamItemImpl implements TeamItem {
  private static final String TEAM_ITEM_NAME_LENGTH_EXCEPTION =
          "%s name length should be between 5 and 15 symbols.";
  private static final int TEAM_ITEM_NAME_MIN_LENGTH = 5;
  private static final int TEAM_ITEM_NAME_MAX_LENGTH = 15;
  private static final String NO_ACTIVITY_HISTORY_MESSAGE =
          "Activity history: There is no activity history for %s %s.%n";
  private static final String ACTIVITY_HISTORY_MESSAGE =
          "Activity history for %s %s: %n";

  private String name;
  private List<WorkItem> workItems;
  private List<String> activityHistory;

  public TeamItemImpl(String name) {
    setName(name);
    workItems = new ArrayList<>();
    activityHistory = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<WorkItem> getWorkItems() {
    return Collections.unmodifiableList(workItems);
  }

  @Override
  public List<String> getActivityHistory() {
    return Collections.unmodifiableList(activityHistory);
  }

  @Override
  public void addWorkItem(WorkItem workItem) {
    workItems.add(workItem);
  }

  @Override
  public void removeWorkItem(WorkItem workItem) {
    workItems.remove(workItem);
  }

  @Override
  public void addToActivityHistory(String message) {
    activityHistory.add(message);
  }

  @Override
  public String showActivityHistory() {
    StringBuilder builder = new StringBuilder();

    builder.append(getActivityHistoryCount());

    getActivityHistoryMessages(builder);
    builder.append(System.lineSeparator());

    return builder.toString();
  }


  public String getType() {
    return this.getClass().getSimpleName().replace("Impl", "");
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(getBaseTeamItemInfo())
            .append(getTeamItemWorkItemCountInfo());

    getTeamItemWorkItemInfo(builder);

    builder.append(showActivityHistory());

    return builder.toString();
  }

  private String getBaseTeamItemInfo() {
    return String.format("%s %s has %d work items and %d history messages.%n",
            getType(),
            getName(),
            getWorkItems().size(),
            getActivityHistory().size());
  }

  private String getTeamItemWorkItemCountInfo() {
    return getWorkItems().isEmpty() ? String.format("Work items: There are no work items for this %s.%n",
            getType().toLowerCase()) :
            (getWorkItems().size() == 1 ? String.format("Work item: %n") : String.format("Work items: %n"));
  }

  private void getTeamItemWorkItemInfo(StringBuilder builder) {
    getWorkItems().forEach(workItem -> builder.append(String.format("%s%n", workItem)));
  }

  private String getActivityHistoryCount() {
    return getActivityHistory().isEmpty() ?
            String.format(NO_ACTIVITY_HISTORY_MESSAGE, getType(), getName()) :
            String.format(ACTIVITY_HISTORY_MESSAGE, getType(), getName());
  }

  private void getActivityHistoryMessages(StringBuilder builder) {
    getActivityHistory().forEach(activity -> builder.append(String.format("%s%n", activity)));
  }

  private void setName(String name) {
    Validator.validateFieldMinAndMaxBoundries(
            name.length(),
            TEAM_ITEM_NAME_MIN_LENGTH,
            TEAM_ITEM_NAME_MAX_LENGTH,
            String.format(TEAM_ITEM_NAME_LENGTH_EXCEPTION, getType()));

    this.name = name;
  }
}
