package ru.monsterdev.domofon.services;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.monsterdev.domofon.config.DomofonContextMemoryDb;
import ru.monsterdev.domofon.domain.OpClient;
import ru.monsterdev.domofon.repositories.OpClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomofonContextMemoryDb.class})
@ActiveProfiles("test")
public class ClientServiceTest {

  @Autowired
  private OpClientRepository clientRepository;

  @Test
  public void test1() {
    List<OpClient> clients = clientRepository.findAll();
    System.out.println(clients);
  }
}
