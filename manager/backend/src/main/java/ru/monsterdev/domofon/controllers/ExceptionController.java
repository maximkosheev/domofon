package ru.monsterdev.domofon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.monsterdev.domofon.dto.ResponseMsgDto;
import ru.monsterdev.domofon.exceptions.AccountExistException;
import ru.monsterdev.domofon.exceptions.AccountMutiplyException;
import ru.monsterdev.domofon.exceptions.CustomException;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;
import ru.monsterdev.domofon.security.JwtAuthenticationException;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({CustomException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseMsgDto customExceptionHandler(CustomException ex) {
    return new ResponseMsgDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getClass().getName(),
        String.format("Возникла нераспознанная ошибка с сообщением: %s", ex.getMessage()));
  }

  @ExceptionHandler({JwtAuthenticationException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseMsgDto jwtAuthenticationHandler(JwtAuthenticationException ex) {
    return new ResponseMsgDto(HttpStatus.UNAUTHORIZED, ex.getClass().getName(), ex.getMessage());
  }

  @ExceptionHandler({BadCredentialsException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseMsgDto badCredentialsHandler(BadCredentialsException ex) {
    return new ResponseMsgDto(HttpStatus.FORBIDDEN, ex.getClass().getName(), ex.getMessage());
  }

  @ExceptionHandler({AccountExistException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseMsgDto accountExistHandler(AccountExistException ex) {
    return new ResponseMsgDto(HttpStatus.BAD_REQUEST, ex.getClass().getName(), ex.getMessage());
  }

  @ExceptionHandler({AccountMutiplyException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseMsgDto accountMultiplyHandler(AccountMutiplyException ex) {
    return new ResponseMsgDto(HttpStatus.BAD_REQUEST, ex.getClass().getName(), ex.getMessage());
  }

  @ExceptionHandler({DeviceNotFoundException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseMsgDto accountExistHandler(DeviceNotFoundException ex) {
    return new ResponseMsgDto(HttpStatus.BAD_REQUEST, ex.getClass().getName(),
        "По указанному адресу домофонное устройство не найдено");
  }
}
