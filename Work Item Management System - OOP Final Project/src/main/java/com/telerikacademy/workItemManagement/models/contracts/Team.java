package com.telerikacademy.workItemManagement.models.contracts;

import java.util.List;

public interface Team {
  String getName();

  List<Member> getMembers();

  List<Board> getBoards();

  void addMember(Member member);

  void addBoard(Board board);
}
