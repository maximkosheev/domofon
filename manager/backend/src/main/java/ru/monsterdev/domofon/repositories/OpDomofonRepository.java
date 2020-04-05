package ru.monsterdev.domofon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.monsterdev.domofon.domain.OpDomofon;

public interface OpDomofonRepository extends JpaRepository<OpDomofon, Long>, QuerydslPredicateExecutor<OpDomofon> {

}
