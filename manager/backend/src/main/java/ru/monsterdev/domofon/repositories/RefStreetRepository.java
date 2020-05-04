package ru.monsterdev.domofon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.monsterdev.domofon.domain.RefStreet;

@Repository
public interface RefStreetRepository extends JpaRepository<RefStreet, Long> {

}
