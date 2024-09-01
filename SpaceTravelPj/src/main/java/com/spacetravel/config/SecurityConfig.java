package com.spacetravel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spacetravel.controller.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin((form) -> form
				.loginPage("/user/login")
				.successHandler(new CustomLoginSuccessHandler())
			)
			.logout((logout) -> logout
	                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	                .logoutSuccessUrl("/user/login")
	                // 기존에 사용된 사용자 세션 삭제
	                .invalidateHttpSession(true))
		
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/css/**", "/js/**", "/images/**", "/", "/user/login", "/user/singUp", "/user/singUpOk").permitAll()
				.anyRequest().authenticated()
			);
		return http.build();
	}

}
