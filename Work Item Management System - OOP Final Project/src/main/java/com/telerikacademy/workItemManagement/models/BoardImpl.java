package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.Board;

public class BoardImpl extends TeamItemImpl implements Board {

  public BoardImpl(String name) {
    super(name);
  }
}