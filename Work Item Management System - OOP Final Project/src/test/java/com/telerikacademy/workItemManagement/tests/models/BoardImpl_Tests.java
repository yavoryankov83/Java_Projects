package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.BoardImpl;
import com.telerikacademy.workItemManagement.models.contracts.Board;
import org.junit.Test;

public class BoardImpl_Tests {
  @Test(expected = IllegalArgumentException.class)
  public void constructor_should_throwException_when_name_lenght_is_less_than_5() {
    // Arrange, Act, Assert
    Board board = new BoardImpl("1234");
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_should_throwException_when_name_lenght_is_more_than_15() {
    // Arrange, Act, Assert
    Board board = new BoardImpl("1234567891234567");
  }
}
