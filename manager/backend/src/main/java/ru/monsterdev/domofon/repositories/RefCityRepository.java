package ru.monsterdev.domofon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.monsterdev.domofon.domain.RefCity;

@Repository
public interface RefCityRepository extends JpaRepository<RefCity, Long> {

}
