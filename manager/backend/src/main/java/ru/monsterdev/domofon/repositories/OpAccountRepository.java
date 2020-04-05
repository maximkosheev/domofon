package ru.monsterdev.domofon.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.monsterdev.domofon.domain.OpAccount;

public interface OpAccountRepository extends JpaRepository<OpAccount, Long>, QuerydslPredicateExecutor<OpAccount> {

  /**
   * Возвращает аккаунт по его лицевому счету
   * @param account лицевой счет
   * @return аккаунт
   */
  Optional<OpAccount> findByAccount(String account);
}
