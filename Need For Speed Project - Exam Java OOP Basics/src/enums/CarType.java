package enums;

public enum CarType {

  PERFORMANCE,
  SHOW;

  @Override
  public String toString() {
    return this.name().charAt(0) + this.name().substring(1).toLowerCase();
  }
}
