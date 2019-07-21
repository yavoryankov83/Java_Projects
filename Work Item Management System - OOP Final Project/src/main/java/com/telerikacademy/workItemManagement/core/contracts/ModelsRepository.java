package com.telerikacademy.workItemManagement.core.contracts;

import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.Map;

public interface ModelsRepository {

  Map<String, Team> getAllTeams();

  void addToTeams(String teamName, Team team);

  Map<String, Member> getAllPeople();

  void addToPeople(String personName, Member member);

  Map<String, Member> getAllMembers();

  void addToMembers(String personName, Member member);

  Map<Long, WorkItem> getAllWorkItems();

  void addToWorkItems(long workitemId, WorkItem workitem);

  void removeFromWorkItems(long workItemId, WorkItem workItem);

  Map<Long, Bug> getAllBugs();

  void addToBugs(long bugId, Bug bug);

  Map<Long, Story> getAllStories();

  void addToStories(long storyId, Story story);

  Map<Long, Feedback> getAllFeedbacks();

  void addToFeedbacks(long feedbackId, Feedback feedback);

  Map<String, String> getMembersAssignedToTeam();

  void addToMembersAssignedToTeam(String memberName, String teamName);

  Map<Long, WorkItem> getAllWorkItemsAlreadyAssigned();

  void addToWorkItemsAlreadyAssigned(long workitemId, WorkItem workitem);

  void removeFromWorkItemsAlreadyAssigned(long workitemId, WorkItem workitem);
}
