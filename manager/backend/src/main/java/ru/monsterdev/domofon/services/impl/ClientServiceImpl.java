package ru.monsterdev.domofon.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.monsterdev.domofon.domain.OpClient;
import ru.monsterdev.domofon.repositories.OpClientRepository;
import ru.monsterdev.domofon.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
  @Autowired
  private OpClientRepository clientRepository;

  @Override
  public List<OpClient> findAllClients() {
    Sort fioSort = Sort.by(Direction.ASC, "fio");
    return clientRepository.findAll(fioSort);
  }
}
