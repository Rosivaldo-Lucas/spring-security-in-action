package com.github.rosivaldolucas.spring_security_in_action;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

class SpringSecurityInActionApplicationTests {

	@Test
	void contextLoads() throws NoSuchAlgorithmException {
		String codeVerifier = this.generateCodeVerifier();
		String codeChallenge = this.generateCodeChallenge(codeVerifier);

		System.out.println("code verifier: " + codeVerifier);
		System.out.println("code challenge: " + codeChallenge);
	}

	private String generateCodeVerifier() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] code = new byte[32];
		secureRandom.nextBytes(code);
		return Base64
				.getUrlEncoder()
				.withoutPadding()
				.encodeToString(code);
	}

	private String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] digested = messageDigest.digest(codeVerifier.getBytes());
		return Base64
				.getUrlEncoder()
				.withoutPadding()
				.encodeToString(digested);
	}

}
