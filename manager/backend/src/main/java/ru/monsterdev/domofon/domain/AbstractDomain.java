package ru.monsterdev.domofon.domain;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractDomain implements Serializable {
  private static final long serialVersionUID = -2654955443934292076L;
}
