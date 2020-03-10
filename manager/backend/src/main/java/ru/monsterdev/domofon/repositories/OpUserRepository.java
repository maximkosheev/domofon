package ru.monsterdev.domofon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.monsterdev.domofon.domain.OpUser;

@Repository
public interface OpUserRepository extends JpaRepository<OpUser, Long>, QuerydslPredicateExecutor<OpUser> {

}
