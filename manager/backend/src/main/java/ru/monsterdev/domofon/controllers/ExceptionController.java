package ru.monsterdev.domofon.controllers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.monsterdev.domofon.dto.ResponseMsg;
import ru.monsterdev.domofon.exceptions.CustomException;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({CustomException.class})
  public ResponseMsg customExceptionHandler(CustomException ex) {
    return new ResponseMsg(String.format("Возникла нераспознанная ошибка с сообщением: %s",
        ex.getMessage()));
  }

  @ExceptionHandler({BadCredentialsException.class})
  public ResponseMsg badCredentialsExceptionHandler(BadCredentialsException ex) {
    return new ResponseMsg(ex.getMessage());
  }
}
