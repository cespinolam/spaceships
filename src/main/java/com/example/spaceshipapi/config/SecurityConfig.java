package com.example.spaceshipapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/spaceships/**", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable(); // Disable CSRF for simplicity in development
    }
}
