package com.soltani.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//desactiver les sessions
       // http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
//        http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/categories/**").permitAll();
        http.authorizeRequests().antMatchers("/products/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new JWTAutorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
