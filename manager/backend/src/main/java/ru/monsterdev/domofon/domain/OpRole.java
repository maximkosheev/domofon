package ru.monsterdev.domofon.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "op_role")
@Data
public class OpRole extends AbstractEntityWithManualId<Integer> {
  public static final String ROLE_ADMIN = "ADMIN";
  public static final String ROLE_MANAGER = "MANAGER";
  public static final String ROLE_CLIENT = "CLIENT";

  private String name;
}
