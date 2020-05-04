package ru.monsterdev.domofon.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AccountRequestDto extends AbstractDto {
  private String account;
  private Long street;
  private String house;
  private String letter;
  private String building;
  private String porch;
  private String flat;
  private String fio;
  private Boolean fsb;
  private Boolean gorod;
  private Boolean hasDevice;
  private Boolean switchOff;
  private String description;
  private DomofonDate connectDate;

  public LocalDate getConnectDate() {
    if (connectDate == null) {
      return null;
    } else {
      return LocalDate.of(connectDate.getYear(), connectDate.getMonth(), connectDate.getDay());
    }
  }
}
