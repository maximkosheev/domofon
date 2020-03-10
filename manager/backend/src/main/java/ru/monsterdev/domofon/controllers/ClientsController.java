package ru.monsterdev.domofon.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.monsterdev.domofon.dto.ClientFilterDto;
import ru.monsterdev.domofon.exceptions.CustomException;
import ru.monsterdev.domofon.services.ClientService;

@Slf4j
@Service
@RequestMapping("/api/v1/clients")
public class ClientsController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/all")
  public ResponseEntity getAllClients(ClientFilterDto clientFilter,
      @RequestParam(value = "page", defaultValue = "1") int page) throws CustomException {
    try {
      clientService.findAllClients(clientFilter, page);
    } catch (Exception ex) {
      log.error("Failed to get clients: ", ex);
      throw new CustomException();
    }
    return ResponseEntity.ok(null);
  }
}
