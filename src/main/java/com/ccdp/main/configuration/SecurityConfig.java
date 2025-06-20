package com.ccdp.main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	 @Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  http
	   .authorizeHttpRequests((auth) -> auth
	    .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
	    .requestMatchers("/home").permitAll()
	    .requestMatchers("/login").permitAll()
	    .requestMatchers("/").permitAll()
	    .requestMatchers("/header").permitAll()
	    .requestMatchers("/css/**").permitAll()
	    .requestMatchers("/css/**").permitAll()
	    .requestMatchers("/images/**").permitAll()
	    .anyRequest().authenticated()
//	    .anyRequest().permitAll()
	   )
	   .formLogin((form) -> form
	    .loginPage("/login")
	    .defaultSuccessUrl("/home", true)
	    .permitAll()
	   )
	   .logout((logout) -> logout
			   .logoutSuccessUrl("/home")
			   .permitAll())
	   .csrf(c -> c.disable());
	  return http.build();
	 }
	
	
}
