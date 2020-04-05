package ru.monsterdev.domofon.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AccountResponseDto extends AbstractDto {
  private Boolean hasDevice;
  private Boolean switchOff;
  private String account;
  private String fio;
  private String phone;
  private Long streetId;
  private String street;
  private String house;
  private String letter;
  private String building;
  private String porch;
  private String flat;
  private LocalDate connectDate;
  private LocalDate disconnectDate;
  private LocalDate createDate;
  private Boolean fsb;
  private Boolean gorod;
  private String description;
}
