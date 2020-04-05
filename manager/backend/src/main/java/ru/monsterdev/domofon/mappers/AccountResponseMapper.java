package ru.monsterdev.domofon.mappers;

import org.springframework.stereotype.Component;
import ru.monsterdev.domofon.domain.OpAccount;
import ru.monsterdev.domofon.dto.AccountResponseDto;

@Component
public class AccountResponseMapper implements Mapper<OpAccount, AccountResponseDto> {

  @Override
  public AccountResponseDto toDto(OpAccount entity) {
    AccountResponseDto dto = new AccountResponseDto();
    dto.setHasDevice(entity.getHasDevice());
    dto.setSwitchOff(entity.getSwitchOff());
    dto.setAccount(entity.getAccount());
    dto.setFio(entity.getFio());
    dto.setPhone(entity.getPhone());
    dto.setStreetId(entity.getDevice().getStreet().getId());
    dto.setStreet(entity.getDevice().getStreet().getName());
    dto.setHouse(entity.getDevice().getHouse());
    dto.setLetter(entity.getDevice().getLetter());
    dto.setBuilding(entity.getDevice().getBuilding());
    dto.setPorch(entity.getDevice().getPorch());
    dto.setFlat(entity.getFlat());
    dto.setConnectDate(entity.getConnectDate());
    dto.setDisconnectDate(entity.getDisconnectDate());
    dto.setCreateDate(entity.getCreateDate());
    dto.setFsb(entity.getFsb());
    dto.setGorod(entity.getGorod());
    dto.setDescription(entity.getDescription());
    return dto;
  }
}
