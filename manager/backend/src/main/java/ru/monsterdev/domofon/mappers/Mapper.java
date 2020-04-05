package ru.monsterdev.domofon.mappers;

import ru.monsterdev.domofon.domain.AbstractDomain;
import ru.monsterdev.domofon.dto.AbstractDto;

/**
 * Базовый интерфейс меперов преобразующих сушьности (entity) в объеты передачи данных (dto) и обратно
 * @param <E> класс, описывающий сущность
 * @param <T> класс, описывающий объект передачи данных
 */
public interface Mapper<E extends AbstractDomain, T extends AbstractDto> {

  /**
   * Преобразует объект передачи данных в экземпляр сущности
   * @param dto объект передачи данных
   * @return экземпляр сущности с заполненными полями
   */
  default E toDomain(T dto) {
    throw new RuntimeException("Method toDomain is not implemented");
  }

  default T toDto(E entity) {
    throw new RuntimeException("Method toDto is not implemented");
  }
}
