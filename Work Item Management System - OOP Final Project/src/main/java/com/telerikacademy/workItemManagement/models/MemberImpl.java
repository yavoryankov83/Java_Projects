package com.telerikacademy.workItemManagement.models;

import com.telerikacademy.workItemManagement.models.contracts.Member;

public class MemberImpl extends TeamItemImpl implements Member {

  public MemberImpl(String name) {
    super(name);
  }
}
