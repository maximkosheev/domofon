package ru.monsterdev.domofon.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class AccountsFilterObject extends PaginationObject {
  private String account;
}
