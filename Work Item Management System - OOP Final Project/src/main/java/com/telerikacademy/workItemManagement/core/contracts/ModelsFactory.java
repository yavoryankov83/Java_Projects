package com.telerikacademy.workItemManagement.core.contracts;

import com.telerikacademy.workItemManagement.models.contracts.*;

public interface ModelsFactory {
  Team createTeam(String name);

  Board createBoard(String name);

  Member createMember(String name);

  Bug createBug(
          String title,
          String description,
          String priority,
          String severity,
          String status,
          Member assignee);

  Story createStory(
          String title,
          String description,
          String priority,
          String size,
          String status,
          Member assignee);

  Feedback createFeedback(
          String title,
          String description,
          int rating,
          String status);

  Comment createComment(Member author, String comment);
}
