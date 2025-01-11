package com.github.rosivaldolucas.spring_security_in_action.config;

import com.github.rosivaldolucas.spring_security_in_action.config.filter.RequestValidationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class WebAuthorizationConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());

    http
            .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers("/api/v1").permitAll()
                            .anyRequest().authenticated()
            );

    return http.build();
  }

}
