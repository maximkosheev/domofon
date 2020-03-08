package ru.monsterdev.domofon.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "op_domofon")
@SequenceGenerator(name = "default_gen", sequenceName = "op_domofon_seq", allocationSize = 1)
public class OpDomofon extends AbstractEntity<Integer> {

  @Column(name = "model", length = 15)
  private String model;

  @Column(name = "description")
  private String description;

  @ManyToOne(optional = false)
  @JoinColumn(name = "street_id")
  private RefStreet street;

  @Column(name = "house", length = 10)
  private String house;

  @Column(name = "letter", length = 5)
  private String letter;

  @Column(name = "building", length = 5)
  private String building;

  @Column(name = "porch", length = 5)
  private String porch;

  @CreatedDate
  @Column(name = "mounting_dt")
  private LocalDate mountingDate;
}
