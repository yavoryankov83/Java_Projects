package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.TeamImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TeamImpl_Tests {
  @Test
  public void getName_should_return_name() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    // Act
    String name = "Ivana";
    // Assert
    Assert.assertEquals(name, team.getName());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getMembers_should_return_UnsupportedOperation_if_add_elements() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    // Act
    List<Member> members = team.getMembers();

    // Assert
    members.add(new MemberImpl("Georgi"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getBoards_should_return_UnsupportedOperation_if_add_elements() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    // Act
    List<Board> boards = team.getBoards();

    // Assert
    boards.add(new BoardImpl("Georgi"));
  }

  @Test
  public void addMember_should_increaseMemberCount() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    // Act
    team.addMember(new MemberImpl("Yavor"));

    // Assert
    Assert.assertEquals(1, team.getMembers().size());
  }

  @Test
  public void addBoard_should_increaseBoardCount() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    // Act
    team.addBoard(new BoardImpl("Yavor"));

    // Assert
    Assert.assertEquals(1, team.getBoards().size());
  }

  @Test
  public void toString_should_returnCorrectOutput() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    StringBuilder builder = new StringBuilder();
    // Act
    builder.append(String.format("Team %s has %d members and %d boards.%n",
            team.getName(),
            team.getMembers().size(),
            team.getBoards().size()));
    builder.append(String.format("Members: There are no members in team.%n"));
    builder.append(String.format("Boards: There are no boards in team.%n"));

    // Assert
    Assert.assertEquals(builder.toString(), team.toString());
  }

  @Test
  public void toString_should_returnCorrectOutputIfAddMemberAndBoard() {
    // Arrange
    Team team = new TeamImpl("Ivana");
    Member member = new MemberImpl("Toshko");
    team.addMember(member);
    Board board = new BoardImpl("Board");
    team.addBoard(board);
    StringBuilder builder = new StringBuilder();
    // Act
    builder.append(String.format("Team %s has %d members and %d boards.%n",
            team.getName(),
            team.getMembers().size(),
            team.getBoards().size()));
    builder.append(String.format("Member: %n"))
            .append(String.format("%s%n", member))
            .append(String.format("Board: %n"))
            .append(String.format("%s%n", board));

    // Assert
    Assert.assertEquals(builder.toString(), team.toString());
  }
}
