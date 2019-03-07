package commands;

import exceptionMessages.ExceptionMessages;
import exceptions.InvalidUnitTypeException;
import exceptions.UnitIsDeadException;
import exceptions.UnitNotExistException;
import interfaces.Command;
import interfaces.Repository;
import interfaces.Unit;

public class StatusCommand implements Command {

  private Repository<Unit> unitRepository;

  public StatusCommand(Repository<Unit> unitRepository) {
    this.unitRepository = unitRepository;
  }

  @Override
  public String execute(String[] args) throws InvalidUnitTypeException, UnitNotExistException, UnitIsDeadException {
    String unitName = args[1];

    Unit unit = this.unitRepository.findByName(unitName);

    if (unit == null) {
      throw new UnitNotExistException(ExceptionMessages.NOT_EXISTING_UNIT_EXCEPTION_MESSAGE);
    }

    if (unit.getHealthPoints() <= 0) {
      throw new UnitIsDeadException(ExceptionMessages.UNIT_IS_DEAD_EXCEPTION_MESSAGE);
    }

    return unit.toString();
  }
}
