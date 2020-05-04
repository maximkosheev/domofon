package ru.monsterdev.domofon.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.domofon.exceptions.CustomException;
import ru.monsterdev.domofon.services.CommonService;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonControllerV1 {

  @Autowired
  private CommonService commonService;

  @GetMapping("/cities")
  public ResponseEntity<?> getCities() {
    try {
      return ResponseEntity.ok(commonService.getCities());
    } catch (Exception ex) {
      log.error("", ex);
      throw new CustomException("Ошибка получения списка городов");
    }
  }

  @GetMapping("/streets")
  public ResponseEntity<?> getStreets() {
    try {
      return ResponseEntity.ok(commonService.getStreets());
    } catch (Exception ex) {
      log.error("", ex);
      throw new CustomException("Ошибка получения списка улиц");
    }
  }
}
