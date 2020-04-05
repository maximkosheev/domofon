package ru.monsterdev.domofon.services.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.time.LocalDate;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.monsterdev.domofon.domain.OpAccount;
import ru.monsterdev.domofon.domain.OpDomofon;
import ru.monsterdev.domofon.domain.QOpAccount;
import ru.monsterdev.domofon.dto.AccountRequestDto;
import ru.monsterdev.domofon.exceptions.AccountDoesNotExistException;
import ru.monsterdev.domofon.exceptions.AccountExistException;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;
import ru.monsterdev.domofon.filters.AccountsFilterObject;
import ru.monsterdev.domofon.repositories.OpAccountRepository;
import ru.monsterdev.domofon.services.AccountService;
import ru.monsterdev.domofon.services.DeviceService;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private OpAccountRepository repository;

  @Autowired
  private DeviceService deviceService;

  private Predicate getFilterPredicate(AccountsFilterObject filterObject) {
    BooleanBuilder builder = new BooleanBuilder();
    QOpAccount qAccount = QOpAccount.opAccount;

    if (filterObject.getAccount() != null) {
      builder.and(qAccount.account.like(filterObject.getAccount() + '%'));
    }
    log.info("Создан предикат для выборки лицевого счета: {}", builder.toString());
    return builder;
  }

  @Override
  public Page<OpAccount> getFilteredAccounts(AccountsFilterObject filterObject) {
    Predicate predicate = getFilterPredicate(filterObject);
    return repository.findAll(predicate, PageRequest.of(filterObject.getPage(),
        filterObject.getSize(),
        filterObject.getDirection(),
        Optional.ofNullable(filterObject.getSortBy()).orElse("account")));
  }

  @Override
  public OpAccount getAccount(String account) throws AccountDoesNotExistException {
    Optional<OpAccount> optAccount = repository.findByAccount(account);
    return optAccount.orElseThrow(() -> new AccountDoesNotExistException(account));
  }

  @Override
  public String createAccount(AccountRequestDto newAccount) throws
      AccountExistException, DeviceNotFoundException {
    try {
      // проверка наличия домофонного аппарата по указанному адресу
      OpDomofon opDomofon = deviceService.getDomofonByAddress(newAccount.getStreetId(), newAccount.getHouse(),
          newAccount.getLetter(), newAccount.getBuilding(), newAccount.getPorch());
      OpAccount opAccount = new OpAccount();
      opAccount.setAccount(newAccount.getAccount());
      opAccount.setDevice(opDomofon);
      opAccount.setFio(newAccount.getFio());
      opAccount.setFlat(newAccount.getFlat());
      opAccount.setCreateDate(LocalDate.now());
      opAccount.setFsb(newAccount.getFsb());
      opAccount.setGorod(newAccount.getGorod());
      opAccount.setHasDevice(newAccount.getHasDevice());
      opAccount.setSwitchOff(newAccount.getSwitchOff());
      repository.save(opAccount);
    } catch (DataIntegrityViolationException ex) {
      if (ex.getCause() instanceof ConstraintViolationException) {
        log.error("Лицевой счет {} уже существует", newAccount.getAccount());
        throw new AccountExistException(newAccount.getAccount());
      } else {
        throw ex;
      }
    } catch (DeviceNotFoundException ex) {
      log.error("По адресу (улица, дом, буква, корпус, подъезд) {}, {}, {}, {}, {} домофонный аппарат не найнен",
          newAccount.getStreetId(), newAccount.getHouse(), newAccount.getLetter(), newAccount.getBuilding(),
          newAccount.getPorch());
      throw ex;
    }
    return newAccount.getAccount();
  }
}
