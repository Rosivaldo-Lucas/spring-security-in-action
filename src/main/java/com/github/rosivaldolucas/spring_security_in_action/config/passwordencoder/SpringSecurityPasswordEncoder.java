package com.github.rosivaldolucas.spring_security_in_action.config.passwordencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SpringSecurityPasswordEncoder {

  public PasswordEncoder getPasswordEncoder(PasswordEncoderType type) {
    return switch (type) {
      case NO_OP_PASSWORD_ENCODER -> this.createNoOpPasswordEncoder();
      case STANDARD_PASSWORD_ENCODER -> this.createStandardPasswordEncoder();
      case PBKDF2_PASSWORD_ENCODER -> this.createPbkdf2PasswordEncoder();
      case BCRYPT_PASSWORD_ENCODER -> this.createBCryptPasswordEncoder();
      case SCRYPT_PASSWORD_ENCODER -> this.createSCryptPasswordEncoder();
    };
  }

  private PasswordEncoder createNoOpPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  private PasswordEncoder createStandardPasswordEncoder() {
    return new StandardPasswordEncoder("secret");
  }

  private PasswordEncoder createPbkdf2PasswordEncoder() {
    return new Pbkdf2PasswordEncoder("secret", 16, 310000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
  }

  private PasswordEncoder createBCryptPasswordEncoder() {
    SecureRandom secureRandom;
    try {
      secureRandom = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return new BCryptPasswordEncoder(4, secureRandom);
  }

  private PasswordEncoder createSCryptPasswordEncoder() {
    return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
  }

  public enum PasswordEncoderType {
    NO_OP_PASSWORD_ENCODER,
    STANDARD_PASSWORD_ENCODER,
    PBKDF2_PASSWORD_ENCODER,
    BCRYPT_PASSWORD_ENCODER,
    SCRYPT_PASSWORD_ENCODER,
  }

}
