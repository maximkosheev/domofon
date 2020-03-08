package ru.monsterdev.domofon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SomeUtils {
  @Test
  //@Ignore
  public void encodePassword() {
    PasswordEncoder encoder = new BCryptPasswordEncoder(8);
    System.out.println(encoder.encode("test"));
  }
}
