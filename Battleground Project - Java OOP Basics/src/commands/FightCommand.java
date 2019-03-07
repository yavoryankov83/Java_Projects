package commands;

import exceptionMessages.ExceptionMessages;
import exceptions.BaseException;
import exceptions.NotEnoughtSpawnForFightException;
import exceptions.UnitOutsideOfRangeException;
import interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FightCommand implements Command {

  private Battleground battleground;
  private Repository<Unit> unitRepository;

  public FightCommand(Battleground battleground, Repository<Unit> unitRepository) {
    this.battleground = battleground;
    this.unitRepository = unitRepository;
  }

  @Override
  public String execute(String[] args) throws BaseException {
    List<Unit> killedUnits = new ArrayList<>();

    List<Unit> allLivedUnits = this.unitRepository
            .findAll()
            .stream()
            .filter(unit -> unit.getHealthPoints() > 0)
            .collect(Collectors.toList());

    if (allLivedUnits.size() < 2) {
      throw new NotEnoughtSpawnForFightException(ExceptionMessages.NOT_ENOUGHT_SPAWN_FOR_FIGHT_EXCEPTION_MESSAGE);
    }

    StringBuilder builder = new StringBuilder();

    for (Unit unit : allLivedUnits) {
      Position currentUnitPosition = unit.getPosition();
      int range = unit.getRange();

      List<Unit> targets = this.battleground.getUnitsInRange(currentUnitPosition.getX(), currentUnitPosition.getY(), range);

      if (targets.isEmpty()) {
        continue;
      }

      Unit target = unit.getCombatHandler().pickNextTarget(targets);

      Spell spell = unit.getCombatHandler().generateAttack();

      if (spell.getDamage() > target.getDefencePoints()) {
        target.setHealthPoints(target.getHealthPoints() - spell.getDamage());

        builder
                .append(String.format("%s cast %s spell and did %d damage",
                        unit.getName(),
                        spell.getClass().getSimpleName(),
                        spell.getDamage()))
                .append(System.lineSeparator());

        if (target.getHealthPoints() <= 0) {
          target.setHealthPoints(0);

          builder
                  .append(String.format("%s killed %s",
                          unit.getName(),
                          target.getName()))
                  .append(System.lineSeparator());
        }
      }
    }

    String result = builder.toString().trim();

    if (result.isEmpty()) {
      throw new UnitOutsideOfRangeException(ExceptionMessages.UNIT_OUTSIDE_OF_RANGE_EXCEPTION_MESSAGE);
    }

    return result;
  }
}
