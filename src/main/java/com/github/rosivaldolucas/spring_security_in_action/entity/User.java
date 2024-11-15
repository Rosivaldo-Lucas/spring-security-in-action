package com.github.rosivaldolucas.spring_security_in_action.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String authority;

  protected User() { }

  public User(String username, String password, String authority) {
    this.id = UUID.randomUUID();
    this.username = username;
    this.password = password;
    this.authority = authority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(username, user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username);
  }

}
