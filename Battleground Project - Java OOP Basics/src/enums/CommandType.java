package enums;

public enum CommandType {
  SPAWN,
  MOVE,
  FIGHT,
  STATUS,
  GAME_OVER;

  @Override
  public String toString() {
    return this.name().toLowerCase().replace("_", "-");
  }
}
