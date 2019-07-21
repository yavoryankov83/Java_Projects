package com.telerikacademy.workItemManagement.tests.models;

import com.telerikacademy.workItemManagement.models.common.Validator;
import org.junit.Test;

public class Validator_Tests {
  @Test(expected = IllegalArgumentException.class)
  public void validateStringNotNullOrEmpty_should_throw_exceprio_when_pass_null_or_empty_value() {
    //Arrange
    String name = null;
    String message = "Message";

    //Act, Assert
    Validator.validateStringNotNullOrEmpty(name, message);
  }

  @Test
  public void validateStringNotNullOrEmpty_should_exit_when_pass_correct_value() {
    //Arrange
    String name = "Name";
    String message = "Message";

    //Act, Assert
    Validator.validateStringNotNullOrEmpty(name, message);
  }
}
