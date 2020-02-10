package ru.monsterdev.domofon.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.monsterdev.domofon.domain.OpRole;

@Component
public class JwtTokenProvider {

  @Value("${jwt.token.secret}")
  private String secret;
  @Value("${jwt.token.expired}")
  private Long expired;

  @Autowired
  private UserDetailsService usersService;

  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String createToken(String username, Set<OpRole> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", getRoleNames(roles));
    Date now = new Date();
    Date validityTo = new Date(now.getTime() + expired);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validityTo)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = usersService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * Возвращает JWT-токен из запроса
   * @param request запрос
   * @return токен
   */
  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }
      return true;
    } catch (JwtException | IllegalArgumentException ex) {
      throw new JwtAuthenticationException("JWT token is expired on invalid");
    }
  }

  private List<String> getRoleNames(Set<OpRole> roles) {
    List<String> result = new ArrayList<>();
    roles.forEach(role -> {
      result.add(role.getName());
    });
    return result;
  }
}
