package com.telerikacademy.workItemManagement.tests.core.providers;

import com.telerikacademy.workItemManagement.core.contracts.Writer;
import com.telerikacademy.workItemManagement.core.providers.ConsoleWriter;
import org.junit.Before;
import org.junit.Test;

public class ConsoleWriter_Tests {
  private Writer writer;

  @Before
  public void initialize(){
    writer = new ConsoleWriter();
  }

  @Test
  public void writeLine_should_write_given_string(){
    String name = "Ivana";

    writer.writeLine(name);
  }
}
