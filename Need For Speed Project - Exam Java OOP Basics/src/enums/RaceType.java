package enums;

public enum RaceType {

  CASUAL,
  DRAG,
  DRIFT;

  @Override
  public String toString() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }
}
