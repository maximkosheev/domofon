package ru.monsterdev.domofon.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.monsterdev.domofon.config.DomofonContextMemoryDb;
import ru.monsterdev.domofon.domain.OpAccount;
import ru.monsterdev.domofon.dto.AccountRequestDto;
import ru.monsterdev.domofon.exceptions.AccountExistException;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomofonContextMemoryDb.class})
@ActiveProfiles("test")
public class AccountServiceTest {

  @Autowired
  private AccountService accountService;

  @Test
  public void addAccount_mustThrowDeviceNotFoundException() {
    AccountRequestDto newAccount = new AccountRequestDto();
    newAccount.setAccount("123");
    newAccount.setStreet(1L);
    newAccount.setBuilding("10");
    newAccount.setLetter("A");
    newAccount.setPorch("12");
    newAccount.setFlat("13");
    newAccount.setFio("Через3забора Ногу Задерищенко");
    assertThrows(
        DeviceNotFoundException.class,
        () -> accountService.createAccount(newAccount)
    );
  }

  @Test
  public void addAccount_mustSuccess() {
    AccountRequestDto newAccount = new AccountRequestDto();
    newAccount.setAccount("123");
    newAccount.setStreet(1L);
    newAccount.setHouse("10");
    newAccount.setLetter("A");
    newAccount.setFlat("13");
    newAccount.setHasDevice(true);
    newAccount.setSwitchOff(false);
    newAccount.setFio("Через3забора Ногу Задерищенко");
    accountService.createAccount(newAccount);
    OpAccount opAccount = accountService.getAccount("123");
    assertNotNull(opAccount);
  }
  
  @Test
  @DisplayName("Should throw AccountExistException")
  public void addCount_shouldThrowAccountExistException() {
    AccountRequestDto newAccount1 = new AccountRequestDto();
    newAccount1.setAccount("123");
    newAccount1.setStreet(1L);
    newAccount1.setHouse("10");
    newAccount1.setLetter("A");
    newAccount1.setFlat("13");
    newAccount1.setHasDevice(true);
    newAccount1.setSwitchOff(false);
    newAccount1.setFio("Через3забора Ногу Задерищенко");
    accountService.createAccount(newAccount1);
    AccountRequestDto newAccount2 = new AccountRequestDto();
    newAccount2.setAccount("123");
    newAccount2.setStreet(1L);
    newAccount2.setHouse("10");
    newAccount2.setLetter("A");
    newAccount2.setFlat("13");
    newAccount2.setHasDevice(true);
    newAccount2.setSwitchOff(false);
    newAccount2.setFio("Через3забора Ногу Задерищенко");
    assertThrows(
        AccountExistException.class,
        () -> accountService.createAccount(newAccount2)
    );
  }
}
