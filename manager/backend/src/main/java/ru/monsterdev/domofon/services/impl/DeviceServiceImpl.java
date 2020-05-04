package ru.monsterdev.domofon.services.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.monsterdev.domofon.domain.OpDomofon;
import ru.monsterdev.domofon.domain.QOpDomofon;
import ru.monsterdev.domofon.exceptions.DeviceNotFoundException;
import ru.monsterdev.domofon.repositories.OpDomofonRepository;
import ru.monsterdev.domofon.services.DeviceService;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

  @Autowired
  private OpDomofonRepository domofonRepository;

  private Predicate getFilterPredicate(Long streetId, String house, String letter, String building, String porch) {
    BooleanBuilder builder = new BooleanBuilder();
    QOpDomofon qDomofon = QOpDomofon.opDomofon;

    if (streetId != null) {
      builder.and(qDomofon.street.id.eq(streetId));
    } else {
      builder.and(qDomofon.street.isNull());
    }
    if (!StringUtils.isEmpty(house)) {
      builder.and(qDomofon.house.eq(house));
    } else {
      builder.and(qDomofon.house.isNull());
    }
    if (!StringUtils.isEmpty(letter)) {
      builder.and(qDomofon.letter.eq(letter));
    } else {
      builder.and(qDomofon.letter.isNull());
    }
    if (!StringUtils.isEmpty(building)) {
      builder.and(qDomofon.building.eq(building));
    } else {
      builder.and(qDomofon.building.isNull());
    }
    if (!StringUtils.isEmpty(porch)) {
      builder.and(qDomofon.porch.eq(porch));
    } else {
      builder.and(qDomofon.porch.isNull());
    }
    log.debug("Сформирован предикат для выборки домофонного аппарата {}", builder.toString());
    return builder;
  }

  @Override
  public Optional<OpDomofon> getDomofonByAddress(Long streetId, String house, String letter, String building, String porch) {
    return domofonRepository.findOne(getFilterPredicate(streetId, house, letter, building, porch));
  }
}
