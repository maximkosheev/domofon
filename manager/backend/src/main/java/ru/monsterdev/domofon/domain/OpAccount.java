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
  @JoinColumn(name = "client_id")
  private OpClient client;

  @ManyToOne(optional = false)
  @JoinColumn(name = "domofon_id")
  private OpDomofon device;

  @Column(name = "account", length = 25, nullable = false)
  private String account;

  @Column(name = "has_device", nullable = false)
  private Boolean has_device = true;

  @Column(name = "device_switch_off", nullable = false)
  private Boolean switchOff = false;

  @Column(name = "room", length = 50)
  private String room;

  @CreatedDate
  @Column(name = "con_dt")
  private LocalDate connectionDate;
}
