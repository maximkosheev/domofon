package ru.monsterdev.domofon.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.domofon.domain.OpAccount;
import ru.monsterdev.domofon.dto.AccountRequestDto;
import ru.monsterdev.domofon.dto.AccountResponseDto;
import ru.monsterdev.domofon.dto.ResponseMsgDto;
import ru.monsterdev.domofon.exceptions.CustomException;
import ru.monsterdev.domofon.exceptions.DomofonException;
import ru.monsterdev.domofon.filters.AccountsFilterObject;
import ru.monsterdev.domofon.mappers.Mapper;
import ru.monsterdev.domofon.services.AccountService;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountControllerV1 {

  @Autowired
  private AccountService accountService;

  @Autowired
  private Mapper<OpAccount, AccountResponseDto> responseMapper;

  @GetMapping("/all")
  public ResponseEntity<List<AccountResponseDto>> getFilteredAccounts(AccountsFilterObject filterObject) {
    try {
      log.info("Конечная точка /api/v1/accounts/all");
      return ResponseEntity.ok(accountService.getFilteredAccounts(filterObject)
          .stream()
          .map(dbAccount -> responseMapper.toDto(dbAccount))
          .collect(Collectors.toList()));
    } catch (Exception ex) {
      log.error("", ex);
      throw new CustomException("Ошибка получения списка аккаунтов, смотри журнал для подробностей");
    }
  }

  @GetMapping("/{account}")
  public ResponseEntity<AccountResponseDto> getAccount(@PathVariable("account") String account) {
    try {
      OpAccount opAccount = accountService.getAccount(account);
      return ResponseEntity.ok(responseMapper.toDto(opAccount));
    } catch (Exception ex) {
      log.error("", ex);
      if (ex instanceof DomofonException) {
        throw ex;
      } else {
        throw new CustomException(String.format("Ошибка получения аккаунта %s, смотри журнал для подробностей", account));
      }
    }
  }

  @PostMapping("/add")
  public ResponseEntity<ResponseMsgDto> addAccount(@RequestBody AccountRequestDto newAccount) {
    try {
      log.info("Конечная точка /api/v1/accounts/add");
      String account = accountService.createAccount(newAccount);
      return ResponseEntity.ok(new ResponseMsgDto(
          HttpStatus.OK, null, String.format("Создан новый аккаунт с идентификаторо %s", account)));
    } catch (Exception ex) {
      log.error("", ex);
      if (ex instanceof DomofonException) {
        throw ex;
      } else {
        throw new CustomException("Ошибка создание нового аккаунта, смотри журнал для подробностей");
      }
    }
  }


}
