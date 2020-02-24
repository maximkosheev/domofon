package ru.monsterdev.domofon.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

@Service
@RequestMapping("/api/v1/clients")
public class ClientsController {

  @GetMapping("")
  public ResponseEntity getAllClients(HttpServletRequest request) {
    //WebUtils.getCookie()
    Cookie[] cookies = request.getCookies();
    return ResponseEntity.ok(null);
  }
}
