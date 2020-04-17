package ru.monsterdev.domofon.domain.view;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "v_account")
@EqualsAndHashCode(of = "id")
public class ViewAccount {
  @Id
  private Long id;

  @Column(name = "account")
  private String account;

  @Column(name = "fio")
  private String fio;

  @Column(name = "phone")
  private String phone;

  @Column(name = "has_device")
  private Boolean hasDevice;

  @Column(name = "device_switch_off")
  private Boolean switchOff;

  @Column(name = "city_name")
  private String cityName;

  @Column(name = "street_name")
  private String streetName;

  @Column(name = "house")
  private String house;

  @Column(name = "letter")
  private String letter;

  @Column(name = "building")
  private String building;

  @Column(name = "porch")
  private String porch;

  @Column(name = "flat")
  private String flat;

  @Column(name = "create_dt")
  private LocalDate createDate;

  @Column(name = "con_dt")
  private LocalDate connectDate;

  @Column(name = "discon_dt")
  private LocalDate disconnectDate;

  @Column(name = "fsb")
  private Boolean fsb;

  @Column(name = "gorod")
  private Boolean gorod;

  @Column(name = "description")
  private String description;
}
