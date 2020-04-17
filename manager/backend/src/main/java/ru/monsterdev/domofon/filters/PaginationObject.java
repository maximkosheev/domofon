package ru.monsterdev.domofon.filters;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
@ToString
public class PaginationObject {
  protected Integer page = 0;
  protected Integer pageSize = 25;
  protected String sortBy;
  protected Direction direction = Direction.ASC;
}
