package interfaces;

public interface Unit {

  String getName();

  int getRange();

  Position getPosition();

  void setPosition(Position position);

  int getHealthPoints();

  void setHealthPoints(int healthPoints);

  int getEnergyPoints();

  void setEnergyPoints(int energyPoints);

  int getAttackPoints();

  int getDefencePoints();

  CombatHandler getCombatHandler();
}
