package com.telerikacademy.workItemManagement.models.common;

public final class UIpProviderImpl {
  private static long uId;

  private UIpProviderImpl() {
  }

  public static long getUId() {
    return ++uId;
  }
}
