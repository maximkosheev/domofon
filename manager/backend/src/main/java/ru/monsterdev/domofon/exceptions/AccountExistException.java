package ru.monsterdev.domofon.exceptions;

public class AccountExistException extends DomofonException {

  public AccountExistException() {
    super();
  }

  public AccountExistException(String account) {
    super(String.format("Лицевой счет %s уже существует", account));
  }
}
