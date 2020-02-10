package ru.monsterdev.domofon.services;

import com.querydsl.core.BooleanBuilder;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.monsterdev.domofon.domain.OpUser;
import ru.monsterdev.domofon.domain.QOpUser;
import ru.monsterdev.domofon.model.AppUser;
import ru.monsterdev.domofon.repositories.UserRepository;
import ru.monsterdev.domofon.utils.RoleUtils;

@Service
@Slf4j
public class UsersService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    OpUser opUser = findByUsername(username);
    if (opUser == null)
      throw new UsernameNotFoundException(String.format("User %s not found", username));
    return new AppUser(opUser.getUsername(), opUser.getPassword(),
        true,  !opUser.getLocked(), true, opUser.getEnabled(),
        RoleUtils.fromRoles(opUser.getRoles()));
  }

  public OpUser findByUsername(String username) {
    QOpUser qOpUser = QOpUser.opUser;
    BooleanBuilder predicate = new BooleanBuilder();
    predicate.and(qOpUser.username.eq(username));
    Optional<OpUser> opUser = userRepository.findOne(predicate);
    return opUser.orElse(null);
  }
}
