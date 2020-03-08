package ru.monsterdev.domofon.services;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.monsterdev.domofon.config.DomofonContextMemoryDb;
import ru.monsterdev.domofon.domain.RefCity;
import ru.monsterdev.domofon.repositories.RefCityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomofonContextMemoryDb.class})
@ActiveProfiles("test")
public class ClientServiceTest {

  @Autowired
  private RefCityRepository cityRepository;

  @Test
  public void test1() {
    List<RefCity> cities = cityRepository.findAll();
    System.out.println(cities);
  }
}
