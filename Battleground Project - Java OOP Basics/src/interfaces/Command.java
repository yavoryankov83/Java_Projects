package interfaces;

import exceptions.BaseException;

public interface Command {

  String execute(String[] args) throws BaseException;
}
