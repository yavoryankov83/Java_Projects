package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.*;
import com.telerikacademy.workItemManagement.models.contracts.*;
import com.telerikacademy.workItemManagement.models.enums.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class AbstractWorkItem_Tests {

  //StoryImpl
  @Test
  public void story_constructor_should_returnStory_when_valuesAreValid() {
    // Arrange, Act
    AbstractWorkItem story = new StoryImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));

    // Assert
    Assert.assertEquals(story.getTitle(), "1234567890");
  }

  @Test(expected = IllegalArgumentException.class)
  public void story_constructor_should_throwException_when_title_is_more_than_50() {
    // Arrange, Act, Assert

    StringBuilder title = new StringBuilder();
    for (int i = 0; i < 51; i++) {
      title.append(i);
    }

    AbstractWorkItem story = new StoryImpl(
            title.toString(),
            "1234567890",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void story_constructor_should_throwException_when_title_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem story = new StoryImpl(
            "123456789",
            "1234567890",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void story_constructor_should_throwException_when_description_is_more_than_500() {
    // Arrange, Act, Assert

    StringBuilder description = new StringBuilder();
    for (int i = 0; i < 501; i++) {
      description.append(i);
    }

    AbstractWorkItem story = new StoryImpl(
            "1234567890",
            description.toString(),
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void story_constructor_should_throwException_when_description_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem story = new StoryImpl(
            "1234567890",
            "123456789",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void story_getActivityHistory_should_returnShallowCopy() {
    // Arrange
    AbstractWorkItem story = new StoryImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));

    // Act
    List<String> supposedShallowCopy = story.getActivityHistory();

    // Assert
    supposedShallowCopy.add("This is commentar.");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void story_getComments_should_returnShallowCopy() {
    // Arrange
    AbstractWorkItem story = new StoryImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SizeType.LARGE,
            StoryStatusType.DONE,
            new MemberImpl("Ivana"));

    // Act
    List<Comment> supposedShallowCopy = story.getComments();

    // Assert
    supposedShallowCopy.add(new CommentImpl(new MemberImpl("Ivana"), "This is comment."));
  }

  //BugImpl
  @Test
  public void bug_constructor_should_returnBug_when_valuesAreValid() {
    // Arrange, Act
    AbstractWorkItem bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    // Assert
    Assert.assertEquals(bug.getTitle(), "1234567890");
  }

  @Test(expected = IllegalArgumentException.class)
  public void bug_constructor_should_throwException_when_title_is_more_than_50() {
    // Arrange, Act, Assert

    StringBuilder title = new StringBuilder();
    for (int i = 0; i < 51; i++) {
      title.append(i);
    }

    AbstractWorkItem bug = new BugImpl(
            title.toString(),
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void bug_constructor_should_throwException_when_title_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem bug = new BugImpl(
            "123456789",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void bug_constructor_should_throwException_when_description_is_more_than_500() {
    // Arrange, Act, Assert

    StringBuilder description = new StringBuilder();
    for (int i = 0; i < 501; i++) {
      description.append(i);
    }

    AbstractWorkItem bug = new BugImpl(
            "1234567890",
            description.toString(),
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void bug_constructor_should_throwException_when_description_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem bug = new BugImpl(
            "1234567890",
            "123456789",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void bug_getActivityHistory_should_returnShallowCopy() {
    // Arrange
    AbstractWorkItem bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    // Act
    List<String> supposedShallowCopy = bug.getActivityHistory();

    // Assert
    supposedShallowCopy.add("This is commentar.");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void bug_getComments_should_returnShallowCopy() {
    // Arrange
    AbstractWorkItem bug = new BugImpl(
            "1234567890",
            "1234567890",
            PriorityType.HIGH,
            SeverityType.CRITICAL,
            BugStatusType.ACTIVE,
            new MemberImpl("Ivana"));

    // Act
    List<Comment> supposedShallowCopy = bug.getComments();

    // Assert
    supposedShallowCopy.add(new CommentImpl(new MemberImpl("Ivana"), "This is comment."));
  }

  //FeedbackImpl
  @Test
  public void feedback_constructor_should_returnFeedback_when_valuesAreValid() {
    // Arrange, Act
    AbstractWorkItem feedback = new FeedbackImpl(
            "1234567890",
            "1234567890",
            20,
            FeedbackStatusType.DONE);

    // Assert
    Assert.assertEquals(feedback.getTitle(), "1234567890");
  }

  @Test(expected = IllegalArgumentException.class)
  public void feedback_constructor_should_throwException_when_title_is_more_than_50() {
    // Arrange, Act, Assert

    StringBuilder title = new StringBuilder();
    for (int i = 0; i < 51; i++) {
      title.append(i);
    }

    AbstractWorkItem feedback = new FeedbackImpl(
            title.toString(),
            "1234567890",
            20,
            FeedbackStatusType.DONE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void feedback_constructor_should_throwException_when_title_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem feedback = new FeedbackImpl(
            "123456789",
            "1234567890",
            20,
            FeedbackStatusType.DONE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void feedback_constructor_should_throwException_when_description_is_more_than_500() {
    // Arrange, Act, Assert

    StringBuilder description = new StringBuilder();
    for (int i = 0; i < 501; i++) {
      description.append(i);
    }

    AbstractWorkItem feedback = new FeedbackImpl(
            "1234567890",
            description.toString(),
            20,
            FeedbackStatusType.DONE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void feedback_constructor_should_throwException_when_description_is_less_than_10() {
    // Arrange, Act, Assert
    AbstractWorkItem feedback = new FeedbackImpl(
            "1234567890",
            "123456789",
            20,
            FeedbackStatusType.DONE);
  }

  @Test
  public void getId_should_returnId_when_call_it() {
    // Arrange
    AbstractWorkItem feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);
    //Act
    // Assert
    Assert.assertEquals(feedback.getId(), feedback.getId());
  }

  @Test
  public void addComment_should_increace_size_of_ArrayList_when_aad() {
    // Arrange
    AbstractWorkItem feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);
    Member member = new MemberImpl("Ivana");
    //Act
    Comment comment = new CommentImpl(member, "Commnentar");
    feedback.addComment(comment);
    // Assert
    Assert.assertEquals(1, feedback.getComments().size());
  }

  @Test
  public void addToActivityHistory_should_increace_size_of_ArrayList_when_aad() {
    // Arrange
    AbstractWorkItem feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);
    Member member = new MemberImpl("Ivana");
    //Act
    String message = "History";
    feedback.addToActivityHistory(message);
    // Assert
    Assert.assertEquals(1, feedback.getActivityHistory().size());
  }

  @Test
  public void getPriority_should_return_feedback_status_type_DONE_when_call() {
    // Arrange
    Feedback feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);

    //Act
    String statusType = "Done";
    // Assert
    Assert.assertEquals(statusType, feedback.getStatus());
  }

  @Test
  public void toString_should_returnCorrectOutput_when_call_and_have_single_input() {
    // Arrange
    Feedback feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);

    StringBuilder builder = new StringBuilder();
    // Act
    builder.append(String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
            feedback.getId(),
            feedback.getTitle(),
            feedback.getDescription(),
            feedback.getComments().size(),
            feedback.getActivityHistory().size()))
            .append(String.format("%s with ID %d:%n", feedback.getType(), feedback.getId()))
            .append(String.format("Rating: %d%n", feedback.getRating()))
            .append(String.format("Status: %s%n", feedback.getStatus()))
            .append(String.format("Comments: There are no comments for this work item.%n"))
            .append(String.format("Activity history: There is no activity history for %s with title %s.%n",
                    feedback.getType(),
                    feedback.getTitle()));

    // Assert
    Assert.assertEquals(builder.toString(), feedback.toString());
  }

  @Test
  public void toString_should_returnCorrectOutput_when_call_and_have_multiple_input() {
    // Arrange
    Feedback feedback = new FeedbackImpl(
            "12345678901",
            "1234567891",
            20,
            FeedbackStatusType.DONE);
    Member member = new MemberImpl("Ivana");

    Comment comment = new CommentImpl(member, "Commnentar");
    String activity = "Activity";
    feedback.addComment(comment);
    feedback.addToActivityHistory(activity);

    StringBuilder builder = new StringBuilder();
    // Act
    builder.append(String.format("WorkItem with ID %d, title %s and description %s, has %d comments and %d history messages.%n",
            feedback.getId(),
            feedback.getTitle(),
            feedback.getDescription(),
            feedback.getComments().size(),
            feedback.getActivityHistory().size()))
            .append(String.format("%s with ID %d:%n", feedback.getType(), feedback.getId()))
            .append(String.format("Rating: %d%n", feedback.getRating()))
            .append(String.format("Status: %s%n", feedback.getStatus()))
            .append(String.format("Comment: %n"))
            .append(String.format("%s", comment))
            .append(String.format("Activity history for %s with title %s: %n",
                    feedback.getType(), feedback.getTitle()))
            .append(String.format("%s%n", activity));

    // Assert
    Assert.assertEquals(builder.toString(), feedback.toString());
  }
}
