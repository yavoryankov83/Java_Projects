package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.StoryImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Story;
import com.telerikacademy.workItemManagement.models.enums.PriorityType;
import com.telerikacademy.workItemManagement.models.enums.SizeType;
import com.telerikacademy.workItemManagement.models.enums.StoryStatusType;
import org.junit.Assert;
import org.junit.Test;

public class StoryImpl_Tests {
  @Test
  public void getPriority_should_return_priority() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Story story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    // Act
    String priority = story.getPriority().toString();
    // Assert
    Assert.assertEquals("High", priority);
  }

  @Test
  public void getSize_should_return_size() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Story story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    // Act
    String size = story.getSize().toString();
    // Assert
    Assert.assertEquals("Large", size);
  }

  @Test
  public void getStatus_should_return_status() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Story story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    // Act
    String status = "Done";
    // Assert
    Assert.assertEquals(status, story.getStatus());
  }

  @Test
  public void getAssignee_should_return_assignee() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Story story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    // Act
    Member member1 = story.getAssignee();
    // Assert
    Assert.assertEquals(member, member1);
  }

  @Test
  public void getStatusType_should_return_statusType() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    Story story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    // Act
    String status = "Done";
    // Assert
    Assert.assertEquals(status, story.getStatus());
  }

  @Test
  public void getAdditionalInfo_should_return_correctOutput() {
    // Arrange
    Member member = new MemberImpl("Ivana");
    StoryImpl story = new StoryImpl("StoryStory", "Description",
            PriorityType.HIGH, SizeType.LARGE, StoryStatusType.DONE, member);
    StringBuilder builder = new StringBuilder();
    // Act
    builder
            .append(String.format("%s with ID %d:%n",
                    story.getType(),
                    story.getId()))
            .append(String.format("Priority: %s%n", story.getPriority()))
            .append(String.format("Size: %s%n", story.getSize()))
            .append(String.format("Status: %s%n", story.getStatus()))
            .append(String.format("Assignee: %s%n", story.getAssignee().getName()));
    // Assert
    Assert.assertEquals(builder.toString(), story.getAdditionalInfo());
  }
}
