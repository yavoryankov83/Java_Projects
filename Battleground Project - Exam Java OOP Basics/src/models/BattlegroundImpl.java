package models;

import exceptionMessages.ExceptionMessages;
import exceptions.InvalidPositionException;
import interfaces.Battleground;
import interfaces.Position;
import interfaces.Unit;

import java.util.ArrayList;
import java.util.List;

public class BattlegroundImpl implements Battleground {

  private Unit[][] battleground;

  public BattlegroundImpl(int rows, int cols) {
    this.battleground = new Unit[rows][cols];
  }

  private void validateUnitPosition(int x, int y) throws InvalidPositionException {
    if (x < 0 || x >= this.battleground.length
            || y < 0 || y >= this.battleground[x].length) {

      throw new InvalidPositionException(ExceptionMessages.INVALID_COORDINATES_EXCEPTION_MESSAGE);
    }
  }

  private void validateEmptyPosition(Unit unit) throws InvalidPositionException {
    if (unit != null) {
      throw new InvalidPositionException(ExceptionMessages.POSITION_NOT_EMPTY_EXCEPTION_MESSAGE);
    }
  }

  @Override
  public List<Unit> getUnitsInRange(int xPosition, int yPosition, int range) {
    List<Unit> targetUnits = new ArrayList<>();

    int startRow = Math.max(0, xPosition - range);
    int endRow = Math.min(xPosition + range, this.battleground.length - 1);
    int startCol = Math.max(0, yPosition - range);
    int endCol = Math.min(yPosition + range, this.battleground[xPosition].length - 1);

    for (int i = startRow; i <= endRow; i++) {
      for (int j = startCol; j <= endCol; j++) {
        if (this.battleground[i][j] != null && i != xPosition && j != yPosition) {
          Unit targetUnit = this.battleground[i][j];
          targetUnits.add(targetUnit);
        }
      }
    }

    return targetUnits;
  }

  @Override
  public void add(Unit unit) throws InvalidPositionException {
    Position position = unit.getPosition();

    this.validateUnitPosition(position.getX(), position.getY());

    this.validateEmptyPosition(this.battleground[position.getX()][position.getY()]);

    this.battleground[position.getX()][position.getY()] = unit;
  }

  @Override
  public void remove(Unit unit) {
    Position position = unit.getPosition();
    this.battleground[position.getX()][position.getY()] = null;
  }

  @Override
  public void move(Unit unit, int xPosition, int yPosition) throws InvalidPositionException {
    Position position = unit.getPosition();

    this.validateUnitPosition(xPosition, yPosition);

    this.validateEmptyPosition(this.battleground[xPosition][yPosition]);

    this.battleground[xPosition][yPosition] = unit;
    this.battleground[position.getX()][position.getY()] = null;

    position.setX(xPosition);
    position.setY(yPosition);
  }
}
