package ru.monsterdev.domofon.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "op_role")
public class OpRole extends AbstractEntityWithManualId<Integer> {
  public static final String ROLE_ADMIN = "ADMIN";
  public static final String ROLE_MANAGER = "MANAGER";
  public static final String ROLE_CLIENT = "CLIENT";

  private String name;
}
