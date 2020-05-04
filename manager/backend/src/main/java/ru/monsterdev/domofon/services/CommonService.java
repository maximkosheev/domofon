package ru.monsterdev.domofon.services;

import java.util.List;
import ru.monsterdev.domofon.domain.RefCity;
import ru.monsterdev.domofon.domain.RefStreet;

public interface CommonService {

  /**
   * Возвращает список городов
   * @return список городов
   */
  List<RefCity> getCities();

  /**
   * Возвращает список улиц
   * @return список улиц
   */
  List<RefStreet> getStreets();
}
