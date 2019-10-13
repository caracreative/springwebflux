package com.calvinx.springwebflux.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User
      .withUsername("admin")
      .password(passwordEncoder().encode("password"))
      .roles("USER")
      .build();
    return new MapReactiveUserDetailsService(user);
  }

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) { // @formatter:off
    http
      .authorizeExchange()
        .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .pathMatchers("/").permitAll()
        .anyExchange().authenticated().and()
        .formLogin().and();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


} // @formatter:on