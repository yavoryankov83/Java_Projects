package commands;

import enums.UnitType;
import exceptionMessages.ExceptionMessages;
import exceptions.InvalidPositionException;
import exceptions.InvalidUnitTypeException;
import factories.UnitFactory;
import interfaces.Battleground;
import interfaces.Command;
import interfaces.Repository;
import interfaces.Unit;

public class SpawnCommand implements Command {

  private Battleground battleground;
  private Repository<Unit> unitRepository;

  public SpawnCommand(Battleground battleground, Repository<Unit> unitRepository) {
    this.battleground = battleground;
    this.unitRepository = unitRepository;
  }

  @Override
  public String execute(String[] args) throws InvalidUnitTypeException, InvalidPositionException {
    String typeUnit = args[1];
    UnitType unitType;

    try {
      unitType = UnitType.valueOf(typeUnit.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new InvalidUnitTypeException(ExceptionMessages.INVALID_UNIT_TYPE_EXCEPTION_MESSAGE);
    }

    String unitName = args[2];
    int xPosition = Integer.parseInt(args[3]);
    int yPosition = Integer.parseInt(args[4]);

    Unit unit = UnitFactory.createUnit(unitType, unitName, xPosition, yPosition);

    this.battleground.add(unit);
    this.unitRepository.save(unit);

    return String.format("%s of type %s has spawn @(%d, %d)", unit.getName(), unitType.toString(), xPosition, yPosition);
  }
}
