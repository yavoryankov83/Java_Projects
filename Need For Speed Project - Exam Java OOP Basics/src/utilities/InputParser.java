package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

  public InputParser() {
  }

  public List<String> parseInput(String input) {
    return new ArrayList<>(Arrays.asList(input.split("\\s+")));
  }
}
