package com.telerikacademy.workItemManagement.tests.commands;

import com.telerikacademy.workItemManagement.commands.addingCommands.AddCommentToWorkItem;
import com.telerikacademy.workItemManagement.core.contracts.ModelsFactory;
import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.core.factories.ModelsFactoryImpl;
import com.telerikacademy.workItemManagement.core.providers.ModelsRepositoryImpl;
import com.telerikacademy.workItemManagement.models.*;
import com.telerikacademy.workItemManagement.models.contracts.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddCommentToWorkItem_Tests {
  private ModelsRepository modelsRepository;
  private ModelsFactory modelsFactory;
  private Feedback feedback;
  private AddCommentToWorkItem addCommentToWorkItem;

  @Before
  public void initialize() {
    modelsRepository = new ModelsRepositoryImpl();
    modelsFactory = new ModelsFactoryImpl();
    feedback = modelsFactory.createFeedback(
            "FeedbackFeedback",
            "DescriptionDescrip",
            4,
            "Done");

    addCommentToWorkItem =
            new AddCommentToWorkItem(modelsRepository, modelsFactory);
  }

  @Test
  public void execute_should_return_correct_string_message_when_member_and_workitem_exist() {
    //Arrange
    Team team = new TeamImpl("Barcelona");
    Board board = new BoardImpl("Penalty");
    Member member = new MemberImpl("NeimarJ");
    Comment comment = new CommentImpl(member, "Message");

    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.addToMembers(member.getName(), member);

    board.addWorkItem(feedback);
    team.addBoard(board);
    team.addMember(member);

    modelsRepository.addToTeams("Barcelona", team);
    modelsRepository.addToMembersAssignedToTeam(member.getName(), team.getName());
    modelsRepository.addToPeople(member.getName(), member);

    //Act
    String execute = addCommentToWorkItem.execute(
            String.valueOf(feedback.getId()),
            member.getName(),
            comment.getMessage());

    //Assert
    Assert.assertEquals(String.format("Comment was created by %s in work item %d.",
            member.getName(),
            feedback.getId()),
            execute);
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_workitem_id_not_exists() {
    //Arrange
    Team team = new TeamImpl("Barcelona");
    Board board = new BoardImpl("Penalty");
    Member member = new MemberImpl("NeimarJ");
    Comment comment = new CommentImpl(member, "Message");

    //board.addWorkItem(feedback);
    team.addBoard(board);
    team.addMember(member);

    modelsRepository.addToTeams("Barcelona", team);
    modelsRepository.addToMembersAssignedToTeam(member.getName(), team.getName());
    modelsRepository.addToPeople(member.getName(), member);

    //Act
    String execute = addCommentToWorkItem.execute(String.valueOf(feedback.getId()),
            member.getName(),
            comment.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void execute_should_throw_exception_when_author_is_not_member() {
    //Arrange
    Team team = new TeamImpl("Barcelona");
    Board board = new BoardImpl("Penalty");
    Member member = new MemberImpl("NeimarJ");
    Comment comment = new CommentImpl(member, "Message");

    board.addWorkItem(feedback);
    team.addBoard(board);
    team.addMember(member);

    modelsRepository.addToTeams("Barcelona", team);
    modelsRepository.addToPeople(member.getName(), member);

    //Act
    addCommentToWorkItem.execute(String.valueOf(feedback.getId()),
            member.getName(),
            comment.getMessage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addCommentToWorkItem_should_throw_exception_when_member_not_exist(){
    //Arrange
    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.getAllMembers().clear();
    Member member = new MemberImpl("Ivana");
    long id = feedback.getId();
    String name = member.getName();

    //Act
    addCommentToWorkItem.execute(String.valueOf(id), name, "Description");
  }

  @Test
  public void addCommentToWorkItem_should_return_correct_string_when_member_and_item_are_not_in_same_team(){
    //Arrange
    Team team = new TeamImpl("Barcelona");
    Board board = new BoardImpl("Penalty");
    Member member = new MemberImpl("Ronaldo");
    Comment comment = new CommentImpl(member, "Message");

    Feedback feedback_2 = modelsFactory.createFeedback(
            "FeedbackFeedback_2",
            "DescriptionDescr", 8,
            "Done");

    modelsRepository.addToWorkItems(feedback.getId(), feedback);
    modelsRepository.addToWorkItems(feedback_2.getId(), feedback_2);
    modelsRepository.addToMembers(member.getName(), member);
    modelsRepository.addToMembersAssignedToTeam(member.getName(), team.getName());
    modelsRepository.addToTeams(team.getName(), team);
    team.addBoard(board);
    board.addWorkItem(feedback);
    team.addMember(member);

    long id = feedback_2.getId();
    String author = "Ronaldo";
    String message = "Message";

    //Act
    String execute = addCommentToWorkItem.execute(String.valueOf(id),
            author,
            message);
    //Assert
    Assert.assertEquals(
            String.format("Author %s is not allowed to give comment " +
                    "to work item with ID %d, because they are not in same team.",
                    author, feedback_2.getId()), execute);
  }
}
