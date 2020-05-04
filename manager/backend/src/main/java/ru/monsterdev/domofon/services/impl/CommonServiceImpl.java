package ru.monsterdev.domofon.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.monsterdev.domofon.domain.RefCity;
import ru.monsterdev.domofon.domain.RefStreet;
import ru.monsterdev.domofon.repositories.RefCityRepository;
import ru.monsterdev.domofon.repositories.RefStreetRepository;
import ru.monsterdev.domofon.services.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

  @Autowired
  private RefCityRepository cityRepository;

  @Autowired
  private RefStreetRepository streetRepository;

  @Override
  public List<RefCity> getCities() {
    return cityRepository.findAll();
  }

  @Override
  public List<RefStreet> getStreets() {
    return streetRepository.findAll();
  }
}
