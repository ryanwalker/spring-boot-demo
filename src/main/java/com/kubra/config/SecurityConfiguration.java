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

  public static final String ADMIN_ROLE = "ADMIN";
  public static final String USER_ROLE = "USER";


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password(passwordEncoder().encode("pass"))
        .roles(USER_ROLE, ADMIN_ROLE)

        .and()

        .withUser("user")
        .password(passwordEncoder().encode("pass"))
        .roles(USER_ROLE)
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/hello").permitAll() // This allow users and anonymous
//        .antMatchers("/hello").anonymous() // This would NOT allow any users, only anonymous requests
        .antMatchers("/hello/user").hasAnyRole(USER_ROLE, ADMIN_ROLE)
        .antMatchers("/hello/admin").hasRole(ADMIN_ROLE)
        .antMatchers("/contacts/secret").hasRole(ADMIN_ROLE)
        .antMatchers("/contacts/**").hasRole(USER_ROLE)
        .anyRequest().authenticated()
        .and()
        .httpBasic();

    // Note this gotcha here: csrf id enabled by default.
    // This mean POST, PUT, DELETE requests wont' work without a token.
    // This is a great example of a Spring PRO/CON: built in security...against yourself if you don't understand it!!
    // Disable CSRF
    http.csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}