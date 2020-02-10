package ru.monsterdev.domofon.utils;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.monsterdev.domofon.domain.OpRole;

public class RoleUtils {
  public static Set<GrantedAuthority> fromRoles(Set<OpRole> roles) {
    return roles.stream().map(opRole -> new SimpleGrantedAuthority(opRole.getName())).collect(Collectors.toSet());
  }
}
