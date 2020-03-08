package ru.monsterdev.domofon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ref_street")
@SequenceGenerator(name = "default_gen", sequenceName = "ref_street_seq", allocationSize = 1)
public class RefStreet extends AbstractEntity<Long> {

  @ManyToOne(optional = false)
  @JoinColumn(name = "city_id")
  private RefCity city;

  @Column(name = "name")
  private String name;
}
