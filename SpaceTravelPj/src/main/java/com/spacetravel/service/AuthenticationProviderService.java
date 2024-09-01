package com.spacetravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
 * 스프링 시큐리티 인증용
 */
public class AuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		// 데이테베이스에서 사용자 세부 정보 검색
		CustomUserDetails user = (CustomUserDetails)loginUserDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			// 암호가 일치하면 세부정보가 포함된 Authentication 인터페이스의 구현을 반환
			return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		} else {
			throw new BadCredentialsException("잘못된 자격 증명(Bad credentials)");
		}
		
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}

}
