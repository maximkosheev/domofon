package ru.monsterdev.domofon.services;

import java.util.Optional;
import ru.monsterdev.domofon.domain.OpDomofon;

public interface DeviceService {

  /**
   * Возвращает домофонный аппарат по адресу его подключения
   * @param streetId идентификатор улицы
   * @param house дом
   * @param letter буква адреса
   * @param building строение/корпус
   * @param porch подъезд
   * @return домофонный аппарат
   */
  Optional<OpDomofon> getDomofonByAddress(Long streetId, String house, String letter, String building, String porch);
}
