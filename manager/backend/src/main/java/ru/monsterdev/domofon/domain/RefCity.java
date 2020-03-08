package ru.monsterdev.domofon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ref_city")
@SequenceGenerator(name = "default_gen", sequenceName = "ref_city_seq", allocationSize = 1)
public class RefCity extends AbstractEntity<Long> {
  @Column(name = "name")
  private String name;
}
