package com.github.rosivaldolucas.spring_security_in_action.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class ProjectConfig {

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

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails userDetails = User
            .withUsername("user")
            .password("user")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(userDetails);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

}
