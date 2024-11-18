package com.github.rosivaldolucas.spring_security_in_action.config.passwordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return this.hashWithSha512(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    String hashedPassword = this.encode(rawPassword);

    return encodedPassword.equals(hashedPassword);
  }

  private String hashWithSha512(String password) {
    StringBuilder result = new StringBuilder();

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");

      byte[] digested = digest.digest(password.getBytes());

      for (byte b : digested) {
        result.append(Integer.toHexString(0xFF & b));
      }
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException("Bad algorithm");
    }

    return result.toString();
  }

}
