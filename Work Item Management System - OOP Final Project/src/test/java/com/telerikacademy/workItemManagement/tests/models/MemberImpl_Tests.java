package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.MemberImpl;
import com.telerikacademy.workItemManagement.models.contracts.Member;
import org.junit.Test;

public class MemberImpl_Tests {
  @Test(expected = IllegalArgumentException.class)
  public void constructor_should_throwException_when_name_lenght_is_less_than_5() {
    // Arrange, Act, Assert
    Member member = new MemberImpl("1234");
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_should_throwException_when_name_lenght_is_more_than_15() {
    // Arrange, Act, Assert
    Member member = new MemberImpl("1234567891234567");
  }
}
