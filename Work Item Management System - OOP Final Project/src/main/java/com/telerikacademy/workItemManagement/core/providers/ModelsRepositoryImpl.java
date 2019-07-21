package com.telerikacademy.workItemManagement.core.providers;

import com.telerikacademy.workItemManagement.core.contracts.ModelsRepository;
import com.telerikacademy.workItemManagement.models.contracts.*;

import java.util.*;

public class ModelsRepositoryImpl implements ModelsRepository {
  private Map<String, Team> teams;
  private Map<String, Member> people;
  private Map<String, Member> members;
  private Map<String, String> membersAssignedToTeam;
  private Map<Long, WorkItem> workitems;
  private Map<Long, Bug> bugs;
  private Map<Long, Story> stories;
  private Map<Long, Feedback> feedbacks;


  private Map<Long, WorkItem> workitemsAlreadyAssigneed;

  public ModelsRepositoryImpl() {
    teams = new HashMap<>();
    people = new HashMap<>();
    members = new HashMap<>();
    membersAssignedToTeam = new HashMap<>();
    workitems = new HashMap<>();
    bugs = new HashMap<>();
    stories = new HashMap<>();
    feedbacks = new HashMap<>();

    workitemsAlreadyAssigneed = new HashMap<>();
  }

  @Override
  public Map<String, Team> getAllTeams() {
    return new HashMap<>(teams);
  }

  @Override
  public void addToTeams(String teamName, Team team) {
    teams.put(teamName, team);
  }

  @Override
  public Map<String, Member> getAllPeople() {
    return new HashMap<>(people);
  }

  @Override
  public void addToPeople(String personName, Member member) {
    people.put(personName, member);
  }

  @Override
  public Map<String, Member> getAllMembers() {
    return new HashMap<>(members);
  }

  @Override
  public void addToMembers(String personName, Member member) {
    members.put(personName, member);
  }

  @Override
  public Map<String, String> getMembersAssignedToTeam() {
    return new HashMap<>(membersAssignedToTeam);
  }

  @Override
  public void addToMembersAssignedToTeam(String memberName, String teamName) {
    membersAssignedToTeam.put(memberName, teamName);
  }

  @Override
  public Map<Long, WorkItem> getAllWorkItems() {
    return new HashMap<>(workitems);
  }

  @Override
  public void addToWorkItems(long workitemId, WorkItem workitem) {
    workitems.put(workitemId, workitem);
  }

  @Override
  public void removeFromWorkItems(long workItemId, WorkItem workItem) {
    workitems.remove(workItemId, workItem);
  }

  @Override
  public Map<Long, Bug> getAllBugs() {
    return new HashMap<>(bugs);
  }

  @Override
  public void addToBugs(long bugId, Bug bug) {
    bugs.put(bugId, bug);
  }

  @Override
  public Map<Long, Story> getAllStories() {
    return new HashMap<>(stories);
  }

  @Override
  public void addToStories(long storyId, Story story) {
    stories.put(storyId, story);
  }

  @Override
  public Map<Long, Feedback> getAllFeedbacks() {
    return new HashMap<>(feedbacks);
  }

  @Override
  public void addToFeedbacks(long feedbackId, Feedback feedback) {
    feedbacks.put(feedbackId, feedback);
  }

  @Override
  public Map<Long, WorkItem> getAllWorkItemsAlreadyAssigned() {
    return new HashMap<>(workitemsAlreadyAssigneed);
  }

  @Override
  public void addToWorkItemsAlreadyAssigned(long workitemId, WorkItem workitem) {
    workitemsAlreadyAssigneed.put(workitemId, workitem);
  }

  @Override
  public void removeFromWorkItemsAlreadyAssigned(long workitemId, WorkItem workitem) {
    workitemsAlreadyAssigneed.remove(workitemId, workitem);
  }
}
