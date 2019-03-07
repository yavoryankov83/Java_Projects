package interfaces;

import exceptions.NotEnoughtEnergyForSpellException;

import java.util.List;

public interface CombatHandler {

  void setUnit(Unit unit);

  Unit pickNextTarget(List<Unit> targetCandidates);

  Spell generateAttack() throws NotEnoughtEnergyForSpellException;
}
