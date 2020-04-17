package ru.monsterdev.domofon.services;

import org.springframework.data.domain.Page;
import ru.monsterdev.domofon.domain.OpAccount;
import ru.monsterdev.domofon.domain.view.ViewAccount;
import ru.monsterdev.domofon.dto.AccountRequestDto;
import ru.monsterdev.domofon.exceptions.AccountDoesNotExistException;
import ru.monsterdev.domofon.exceptions.AccountExistException;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;
import ru.monsterdev.domofon.filters.AccountsFilterObject;

public interface AccountService {

  /**
   * Возвращает список аккаунтов (лицевые счета) отфильтрованные и отсортированные по заданным критериям
   * @param filterObject критерии фильрации и сортировки
   * @return список аккаунтов
   */
  Page<ViewAccount> getFilteredAccounts(AccountsFilterObject filterObject);

  /**
   * Создает новый аккаунт
   * @param newAccount информация по новому аккаунту
   * @return идентификатор нового аккаунта
   * @throws AccountExistException выбрасывается в случае существования аккаунта с заданным лицевым счетом
   * @throws DeviceNotFoundException выбраывается в случае отсутствия домофонного-аппарата по указанному адресу
   */
  String createAccount(AccountRequestDto newAccount) throws AccountExistException, DeviceNotFoundException;

  /**
   * Созвращает аккаунт по его идентификатору
   * @param account идентификатор
   * @return аккаунт
   */
  OpAccount getAccount(String account) throws AccountDoesNotExistException;
}
