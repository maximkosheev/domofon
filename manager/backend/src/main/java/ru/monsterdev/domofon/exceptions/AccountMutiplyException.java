package ru.monsterdev.domofon.exceptions;

public class AccountMutiplyException extends DomofonException {

  public AccountMutiplyException() {
    super("По данному адресу уже есть зарегистрированный лицевой счет");
  }
}