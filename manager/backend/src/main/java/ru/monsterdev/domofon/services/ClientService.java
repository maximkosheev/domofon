package ru.monsterdev.domofon.services;

import java.util.List;
import ru.monsterdev.domofon.domain.OpClient;

public interface ClientService {

  /**
   * Возвращает список всех клиентов
   * @return список всех клиентов
   */
  List<OpClient> findAllClients();
}
