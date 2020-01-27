package ru.monsterdev.domofon.domain;

import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "op_user")
@SequenceGenerator(name = "default_gen", sequenceName = "user_seq", allocationSize = 1)
public class OpUser extends AbstractEntity<Long> {

  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<OpRole> roles;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "locked")
  private Boolean locked;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_dt")
  private Calendar create_dt;

  @Override
  public String toString() {
    return "OpUser{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", roles=" + roles +
        ", locked=" + locked +
        ", create_dt=" + create_dt +
        '}';
  }
}
