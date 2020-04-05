package ru.monsterdev.domofon.domain;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Класс абстрактной сущности. Является базовым классом для всех сущностей приложения
 * @param <T>
 */
@MappedSuperclass
public abstract class AbstractEntity<T> extends AbstractDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
  private T id;

  public boolean isNew() {
    return id == null;
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (id == null)
      return false;
    if (obj instanceof AbstractEntity && obj.getClass().equals(getClass()))
      return id.equals(((AbstractEntity)obj).id);
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
