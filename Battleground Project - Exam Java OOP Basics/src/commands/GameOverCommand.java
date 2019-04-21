package commands;

import interfaces.Battleground;
import interfaces.Command;

public class GameOverCommand implements Command {

  @Override
  public String execute(String[] args) {
    return "Game over!";
  }
}
