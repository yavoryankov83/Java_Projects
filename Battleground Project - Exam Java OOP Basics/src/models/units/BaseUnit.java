package models.units;

import interfaces.CombatHandler;
import interfaces.Position;
import interfaces.Unit;

public abstract class BaseUnit implements Unit {

  protected static final int HEALTH_POINTS = 50;
  protected static final int ENERGY_POINTS = 80;
  protected static final int ATTACK_POINTS = 15;
  protected static final int DEFENCE_POINTS = 5;

  private String name;
  private int range;
  private Position position;
  private int healthPoints;
  private int energyPoints;
  private int attackPoints;
  private int defencePoints;
  private CombatHandler combatHandler;

  protected BaseUnit(
          String name,
          int range,
          Position position,
          int healthPoints,
          int energyPoints,
          int attackPoints,
          int defencePoints,
          CombatHandler combatHandler) {
    this.name = name;
    this.range = range;
    this.healthPoints = healthPoints;
    this.energyPoints = energyPoints;
    this.attackPoints = attackPoints;
    this.defencePoints = defencePoints;
    this.combatHandler = combatHandler;
    this.combatHandler.setUnit(this);
    this.position = position;
  }


  public String getName() {
    return this.name;
  }

  public int getRange() {
    return this.range;
  }

  public int getHealthPoints() {
    return this.healthPoints;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  public int getEnergyPoints() {
    return this.energyPoints;
  }

  public void setEnergyPoints(int energyPoints) {
    this.energyPoints = energyPoints;
  }

  public int getAttackPoints() {
    return this.attackPoints;
  }

  public int getDefencePoints() {
    return this.defencePoints;
  }

  public CombatHandler getCombatHandler() {
    return this.combatHandler;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder
            .append(String.format("-> %s", this.name))
            .append(System.lineSeparator())
            .append(String.format("- Type: %s", this.getClass().getSimpleName()))
            .append(System.lineSeparator())
            .append(String.format("- Position: %s", this.position.toString()))
            .append(System.lineSeparator())
            .append(String.format("- Attack Points: %d", this.getAttackPoints()))
            .append(System.lineSeparator())
            .append(String.format("- Deffence Points: %d", this.getDefencePoints()))
            .append(System.lineSeparator())
            .append(String.format("- Energy Points: %d", this.getEnergyPoints()))
            .append(System.lineSeparator())
            .append(String.format("- Health Points: %d", this.getHealthPoints()));

    return builder.toString();
  }
}
