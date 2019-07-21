package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.CommentImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Comment;
import org.junit.Assert;
import org.junit.Test;

public class CommentImpl_Tests {
  @Test
  public void constructor_should_returnComment_when_valuesAreValid() {
    // Arrange, Act
    Comment comment = new CommentImpl(
            new MemberImpl("Ivana"),
            "Comment");

    // Assert
    Assert.assertEquals(comment.getAuthor().getName(), "Ivana");
  }

  @Test
  public void toString_should_returnCorrectOutput_when_valuesAreValid() {
    // Arrange, Act
    Comment comment = new CommentImpl(
            new MemberImpl("Ivana"),
            "Comment");
    String output = String.format("Author: %s, comment: %s%n", comment.getAuthor().getName(), comment.getMessage());

    // Assert
    Assert.assertEquals(output, comment.toString());
  }
}
