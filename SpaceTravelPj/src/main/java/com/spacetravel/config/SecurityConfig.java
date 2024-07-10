package com.spacetravel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/*
	 * 유저 기능 생성 전 임시 설정
	 */
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//http.csrf(CsrfConfigurer::disable);
		
		http.formLogin(AbstractHttpConfigurer::disable);
		
		http.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().permitAll());
		
		return http.build();
		
	}

}
