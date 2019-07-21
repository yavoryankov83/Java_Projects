package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.Board;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import com.telerikacademy.workItemManagement.models.contracts.Team;

import java.util.*;

public class TeamImpl implements Team {
  private String name;
  private List<Member> members;
  private List<Board> boards;

  public TeamImpl(String name) {
    this.name = name;
    members = new ArrayList<>();
    boards = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Member> getMembers() {
    return Collections.unmodifiableList(members);
  }

  @Override
  public List<Board> getBoards() {
    return Collections.unmodifiableList(boards);
  }

  @Override
  public void addMember(Member member) {
    members.add(member);
  }

  @Override
  public void addBoard(Board board) {
    boards.add(board);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder
            .append(getBaseTeamInfo())
            .append(getTeamMembersCountInfo());

    getTeamMembersInfo(builder);

    builder.append(getTeamBoardsCountInfo());

    getTeamBoardsInfo(builder);

    return builder.toString();
  }

  private String getBaseTeamInfo() {
    return String.format("Team %s has %d members and %d boards.%n",
            getName(),
            getMembers().size(),
            getBoards().size());
  }

  private String getTeamMembersCountInfo() {
    return getMembers().isEmpty() ? String.format("Members: There are no members in team.%n") :
            (getMembers().size() == 1 ? String.format("Member: %n") : String.format("Members: %n"));
  }

  private void getTeamMembersInfo(StringBuilder builder) {
    getMembers().forEach(member -> builder.append(String.format("%s%n", member)));
  }

  private String getTeamBoardsCountInfo() {
    return getBoards().isEmpty() ? String.format("Boards: There are no boards in team.%n") :
            (getBoards().size() == 1 ? String.format("Board: %n") : String.format("Boards: %n"));
  }

  private void getTeamBoardsInfo(StringBuilder builder) {
    getBoards().forEach(board -> builder.append(String.format("%s%n", board)));
  }
}