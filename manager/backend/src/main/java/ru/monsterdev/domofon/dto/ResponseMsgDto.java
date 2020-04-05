package ru.monsterdev.domofon.dto;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ResponseMsgDto {
  private HttpStatus status;
  private String error;
  private final String message;
}
