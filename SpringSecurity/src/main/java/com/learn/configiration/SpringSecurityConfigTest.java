package com.learn.configiration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Author  : Thinesh
 * Version : V1
 */

//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfigTest extends WebSecurityConfigurerAdapter {

    // Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Thinesh").password("1234").roles("USER");
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.antMatcher("/rest/security/**").authorizeRequests()
                .anyRequest().fullyAuthenticated().and().httpBasic();
    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
