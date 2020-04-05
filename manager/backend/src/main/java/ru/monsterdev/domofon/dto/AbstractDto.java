package ru.monsterdev.domofon.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public abstract class AbstractDto implements Serializable {
  private static final long serialVersionUID = 858211755871222418L;
}
