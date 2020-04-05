package ru.monsterdev.domofon.services;

import ru.monsterdev.domofon.domain.OpDomofon;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;

public interface DeviceService {

  /**
   * Возвращает домофонный аппарат по адресу его подключения
   * @param streetId идентификатор улицы
   * @param house дом
   * @param letter буква адреса
   * @param building строение/корпус
   * @param porch подъезд
   * @return домофонный аппарат
   * @throws DeviceNotFoundException выбрасывается, если по указанному адресу нет домофонного аппарата
   */
  OpDomofon getDomofonByAddress(Long streetId, String house, String letter, String building, String porch)
      throws DeviceNotFoundException;
}
