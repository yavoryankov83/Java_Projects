package com.telerikacademy.workItemManagement.core.factories;

import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.models.*;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.*;

public class ModelsFactoryImpl implements ModelsFactory {

  @Override
  public Team createTeam(String name) {
    return new TeamImpl(name);
  }

  @Override
  public Board createBoard(String name) {
    return new BoardImpl(name);
  }

  @Override
  public Member createMember(String name) {
    return new MemberImpl(name);
  }

  @Override
  public Bug createBug(
          String title,
          String description,
          String priority,
          String severity,
          String status,
          Member assignee) {
    return new BugImpl(
            title,
            description,
            getPriorityType(priority),
            getSeverityType(severity),
            getBugStatusType(status),
            assignee);
  }

  @Override
  public Story createStory(
          String title,
          String description,
          String priority,
          String size,
          String status,
          Member assignee) {
    return new StoryImpl(title,
            description,
            getPriorityType(priority),
            getSizeType(size),
            getStoryStatusType(status),
            assignee);
  }

  @Override
  public Feedback createFeedback(
          String title,
          String description,
          int rating,
          String status) {
    return new FeedbackImpl(title, description, rating, getFeedbackStatusType(status));
  }

  @Override
  public Comment createComment(Member author, String message) {
    return new CommentImpl(author, message);
  }

  private PriorityType getPriorityType(String priority) {
    return PriorityType.valueOf(priority.toUpperCase());
  }

  private SeverityType getSeverityType(String severity) {
    return SeverityType.valueOf(severity.toUpperCase());
  }

  private SizeType getSizeType(String size) {
    return SizeType.valueOf(size.toUpperCase());
  }

  private BugStatusType getBugStatusType(String status) {
    return BugStatusType.valueOf(status.toUpperCase());
  }

  private StoryStatusType getStoryStatusType(String status) {
    return StoryStatusType.valueOf(status.toUpperCase().replaceAll(" ", "_"));
  }

  private FeedbackStatusType getFeedbackStatusType(String status) {
    return FeedbackStatusType.valueOf(status.toUpperCase());
  }
}
