package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.Comment;
import com.telerikacademy.workItemManagement.models.contracts.WorkItem;
import com.telerikacademy.workItemManagement.models.common.UIpProviderImpl;
import com.telerikacademy.workItemManagement.models.common.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractWorkItem implements WorkItem {
  private static final int WORKITEM_TITLE_MIN_LENGTH = 10;
  private static final int WORKITEM_TITLE_MAX_LENGTH = 50;
  private static final String WORKITEM_TITLE_LENGTH_EXCEPTION =
          "Work item title length should be between 10 and 50 symbols.";
  private static final int WORKITEM_DESCRIPTION_MIN_LENGTH = 10;
  private static final int WORKITEM_DESCRIPTION_MAX_LENGTH = 500;
  private static final String WORKITEM_DESCRIPTION_LENGTH_EXCEPTION =
          "Work item description length should be between 10 and 500 symbols.";
  private static final String NO_ACTIVITY_HISTORY_MESSAGE =
          "Activity history: There is no activity history for %s with title %s.%n";
  private static final String ACTIVITY_HISTORY_MESSAGE =
          "Activity history for %s with title %s: %n";

  private long id;
  private String title;
  private String description;
  private List<Comment> comments;
  private List<String> activityHistory;

  AbstractWorkItem(String title, String description) {
    setTitle(title);
    setDescription(description);
    id = UIpProviderImpl.getUId();
    comments = new ArrayList<>();
    activityHistory = new ArrayList<>();
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public List<Comment> getComments() {
    return Collections.unmodifiableList(comments);
  }

  @Override
  public void addComment(Comment comment) {
    comments.add(comment);
  }

  @Override
  public List<String> getActivityHistory() {
    return Collections.unmodifiableList(activityHistory);
  }

  @Override
  public void addToActivityHistory(String message) {
    activityHistory.add(message);
  }

  @Override
  public String showActivityHistory() {
    StringBuilder builder = new StringBuilder();

    builder.append(getActivityHistoryCountInfo());

    getActivityHistoryMessagesInfo(builder);

    return builder.toString();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getBaseWorkItemInfo())
            .append(getAdditionalInfo())
            .append(getWorkItemCommentsCountInfo());
    getWorkItemCommentsInfo(builder);
    builder.append(showActivityHistory());

    return builder.toString();
  }

  public abstract String getAdditionalInfo();

  public String getType() {
    return this.getClass().getSimpleName().replace("Impl", "");
  }

  private String getBaseWorkItemInfo() {
    return String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
            getId(),
            getTitle(),
            getDescription(),
            getComments().size(),
            getActivityHistory().size());
  }

  private String getWorkItemCommentsCountInfo() {
    return getComments().isEmpty() ? String.format("Comments: There are no comments for this work item.%n") :
            (getComments().size() == 1 ? String.format("Comment: %n") : String.format("Comments: %n"));
  }

  private void getWorkItemCommentsInfo(StringBuilder builder) {
    getComments().forEach(comment -> builder.append(String.format("%s", comment)));
  }

  private String getActivityHistoryCountInfo() {
    return getActivityHistory().isEmpty() ?
            String.format(NO_ACTIVITY_HISTORY_MESSAGE, getType(), getTitle()) :
            String.format(ACTIVITY_HISTORY_MESSAGE, getType(), getTitle());
  }

  private void getActivityHistoryMessagesInfo(StringBuilder builder) {
    getActivityHistory().forEach(activity -> builder.append(String.format("%s%n", activity)));
  }

  private void setTitle(String title) {
    Validator.validateFieldMinAndMaxBoundries(
            title.length(),
            WORKITEM_TITLE_MIN_LENGTH,
            WORKITEM_TITLE_MAX_LENGTH,
            WORKITEM_TITLE_LENGTH_EXCEPTION);

    this.title = title;
  }

  private void setDescription(String description) {
    Validator.validateFieldMinAndMaxBoundries(
            description.length(),
            WORKITEM_DESCRIPTION_MIN_LENGTH,
            WORKITEM_DESCRIPTION_MAX_LENGTH,
            WORKITEM_DESCRIPTION_LENGTH_EXCEPTION);

    this.description = description;
  }
}
