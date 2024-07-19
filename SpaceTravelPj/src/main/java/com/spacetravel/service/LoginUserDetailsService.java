package com.spacetravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.UserMapper;
/*
 * 스프링 시큐리티 인증용
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO u = userMapper.findByUsername(username);
		// DB에 유저 아이디가 없으면
		if(u == null) {
			throw new UsernameNotFoundException("인증 중 문제가 발생했습니다(Problem during authentication)!");
	    }
		// CustomUserDetails 데코레이터로 래핑하고 반환
		return new CustomUserDetails(u);
	}
}
