package com.github.rosivaldolucas.spring_security_in_action.config.passwordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class Base64PasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes()).equals(encodedPassword);
  }

}
