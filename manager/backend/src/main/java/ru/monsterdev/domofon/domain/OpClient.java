package ru.monsterdev.domofon.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "op_client")
@SequenceGenerator(name = "default_gen", sequenceName = "op_client_seq", allocationSize = 1)
public class OpClient extends AbstractEntity<Long> {
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "fio")
  private String fio;

  @ManyToOne
  @JoinColumn(name = "street_id")
  private RefStreet street;

  @Column(name = "house", length = 10)
  private String house;

  @Column(name = "letter", length = 5)
  private String letter;

  @Column(name = "building", length = 5)
  private String building;

  @Column(name = "room", length = 5)
  private String room;

  @Column(name = "phone", length = 25)
  private String phone;

  @Column(name = "description")
  private String description;

  @Column(name = "fsb")
  private Boolean fsb = true;

  @Column(name = "gorod")
  private Boolean gorod = true;

  @CreatedDate
  @Column(name = "create_dt")
  private LocalDate createDate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
  private Set<OpAccount> accounts;
}
