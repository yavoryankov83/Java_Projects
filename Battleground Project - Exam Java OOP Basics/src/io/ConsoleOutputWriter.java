package io;

import interfaces.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {

  @Override
  public void writeLine(String line) {
    System.out.println(line);
  }
}