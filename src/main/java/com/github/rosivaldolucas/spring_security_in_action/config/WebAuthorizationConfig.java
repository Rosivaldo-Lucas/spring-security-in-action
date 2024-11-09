package com.github.rosivaldolucas.spring_security_in_action.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class WebAuthorizationConfig {

  private final CustomAuthenticationProvider customAuthenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());

    http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/api/v1").permitAll()
            .anyRequest().authenticated());

    http.authenticationProvider(this.customAuthenticationProvider);

    return http.build();
  }

}
