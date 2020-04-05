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
@Table(name = "op_account")
@SequenceGenerator(name = "default_gen", sequenceName = "op_account_seq", allocationSize = 1)
public class OpAccount extends AbstractEntity<Long> {

  @ManyToOne(optional = false)
  @JoinColumn(name = "domofon_id")
  private OpDomofon device;

  @Column(name = "account", length = 25, nullable = false)
  private String account;

  @Column(name = "fio")
  private String fio;

  @Column(name = "phone")
  private String phone;

  @Column(name = "has_device", nullable = false)
  private Boolean hasDevice = true;

  @Column(name = "device_switch_off", nullable = false)
  private Boolean switchOff = false;

  @Column(name = "flat", length = 50)
  private String flat;

  @Column(name = "con_dt")
  private LocalDate connectDate;

  @Column(name = "discon_dt")
  private LocalDate disconnectDate;

  @CreatedDate
  @Column(name = "create_dt")
  private LocalDate createDate;

  @Column(name = "fsb")
  private Boolean fsb = true;

  @Column(name = "gorod")
  private Boolean gorod = true;

  @Column(name = "description")
  private String description;
}
