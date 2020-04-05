package ru.monsterdev.domofon.exceptions;

public class AccountDoesNotExistException extends DomofonException {

  public AccountDoesNotExistException() {
    super();
  }

  public AccountDoesNotExistException(String account) {
    super(String.format("Лицевой счет %s не уже существует", account));
  }
}
