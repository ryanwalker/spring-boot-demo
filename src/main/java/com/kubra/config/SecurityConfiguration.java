package com.kubra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin").password(passwordEncoder().encode("pass"))
        .roles("USER", "ADMIN")

        .and()

        .withUser("user").password(passwordEncoder().encode("pass"))
        .roles("USER")
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/hello").permitAll() // This allow users and anonymous
        .antMatchers("/manage/**").permitAll()
//        .antMatchers("/hello").anonymous() // This would ONLY allow anonymous, NOT users
        .antMatchers("/hello/user").hasAnyRole("USER", "ADMIN")//, "ADMIN")
        .antMatchers("/hello/admin").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}