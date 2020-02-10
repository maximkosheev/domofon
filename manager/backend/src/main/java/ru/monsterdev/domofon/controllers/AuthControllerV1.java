package ru.monsterdev.domofon.controllers;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.domofon.domain.OpUser;
import ru.monsterdev.domofon.dto.AuthenticationRequestDto;
import ru.monsterdev.domofon.security.JwtTokenProvider;
import ru.monsterdev.domofon.services.UsersService;

@RestController
@RequestMapping(value = "/api/v1/auth")
@Slf4j
public class AuthControllerV1 {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UsersService usersService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("login")
  public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
    try {
      String username = requestDto.getUsername();

      log.info("Попытка входа пользователя {}", username);
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

      OpUser opUser = usersService.findByUsername(username);
      if (opUser == null) {
        log.error("Пользователь {} не найден", username);
        throw new UsernameNotFoundException("Пользователь " + username + " не найден");
      }
      String token = jwtTokenProvider.createToken(username, opUser.getRoles());
      Map<String, Object> response = new HashMap<>();
      response.put("username", username);
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException ex) {
      log.error("Ошибка авторизации пользователя {}: ", requestDto.getUsername(), ex);
      throw new BadCredentialsException("Имя пользователя или пароль заданы неверно");
    }
  }
}
