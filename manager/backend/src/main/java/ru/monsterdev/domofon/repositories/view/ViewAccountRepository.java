package ru.monsterdev.domofon.repositories.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.monsterdev.domofon.domain.view.ViewAccount;

public interface ViewAccountRepository extends JpaRepository<ViewAccount, Long>, QuerydslPredicateExecutor<ViewAccount> {

}
