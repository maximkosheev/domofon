package ru.monsterdev.domofon.dto;

import lombok.Data;

@Data
public class AccountRequestDto extends AbstractDto {
  private String account;
  private Long streetId;
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
}
