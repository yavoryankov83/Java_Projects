package com.telerikacademy.workItemManagement.tests.core.providers;

import com.telerikacademy.workItemManagement.core.contracts.CommandParser;
import com.telerikacademy.workItemManagement.core.providers.CommandParserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandParserImpl_Tests {
  private CommandParser commandParser;
  @Before
  public void initialize(){
    commandParser = new CommandParserImpl();
  }

  @Test
  public void parseCommand_should_return_command(){
    //Arrange
    String input = "command first second";
    String output = "command";
    //Act
    String result = commandParser.parseCommand(input);
    //Assert
    Assert.assertEquals(output, result);
  }

  @Test
  public void parseParameters_should_return_parameters(){
    //Arrange
    String input = "command first second";
    String[] output = {"first", "second"};
    //Act
    String[] result = commandParser.parseParameters(input);
    //Assert
    Assert.assertArrayEquals(output, result);
  }
}
