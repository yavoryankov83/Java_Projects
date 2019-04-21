package commands;

import exceptionMessages.ExceptionMessages;
import exceptions.BaseException;
import exceptions.UnitNotExistException;
import interfaces.Battleground;
import interfaces.Command;
import interfaces.Repository;
import interfaces.Unit;

public class MoveCommand implements Command {

  private Battleground battleground;
  private Repository<Unit> unitRepository;

  public MoveCommand(Battleground battleground, Repository<Unit> unitRepository) {
    this.battleground = battleground;
    this.unitRepository = unitRepository;
  }

  @Override
  public String execute(String[] args) throws BaseException {
    String unitName = args[1];
    int xPosition = Integer.parseInt(args[2]);
    int yPosition = Integer.parseInt(args[3]);

    Unit unit = this.unitRepository.findByName(unitName);

    if (unit == null) {
      throw new UnitNotExistException(ExceptionMessages.NOT_EXISTING_UNIT_EXCEPTION_MESSAGE);
    }

    this.battleground.move(unit, xPosition, yPosition);

    return String.format("%s moved to %s", unit.getName(), unit.getPosition().toString());
  }
}
