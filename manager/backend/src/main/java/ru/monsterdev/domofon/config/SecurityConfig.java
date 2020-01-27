package ru.monsterdev.domofon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import ru.monsterdev.domofon.domain.OpRole;
import ru.monsterdev.domofon.security.JwtConfigurer;
import ru.monsterdev.domofon.security.JwtTokenProvider;
import ru.monsterdev.domofon.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public UserDetailsService userDetailsService() {
    return new UsersService();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .exceptionHandling()
        //.authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/auth").permitAll()
        .antMatchers("/api/**").hasRole(OpRole.ROLE_MANAGER)
        .and()
        .formLogin().loginProcessingUrl("/api/auth")
        .successForwardUrl("/api/clients")
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}
