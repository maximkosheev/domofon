package ru.monsterdev.domofon.controllers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.monsterdev.domofon.dto.ResponseMsg;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({BadCredentialsException.class})
  public ResponseMsg badCredentialsExceptionHandler(BadCredentialsException ex) {
    ResponseMsg msg = new ResponseMsg(ex.getMessage());
    return msg;
  }
}
