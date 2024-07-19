package com.spacetravel.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.UserMapper;
/*
 * 스프링 시큐리티 인증 구현용
 */
public class CustomUserDetails implements UserDetails {
	
	private final UserDTO userDTO;
	
	public CustomUserDetails(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	@Autowired
	private UserMapper userMapper;
	
	// 해당 유저의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Collection<GrantedAuthority> collect = new ArrayList<>();
	        collect.add(new GrantedAuthority() {
	            @Override
	            public String getAuthority() {
	                return userMapper.selectUserRolesByUsername(userDTO.getUsername());
	            }
	        });
	        return null;
	}

	@Override
	public String getPassword() {
		return userDTO.getPassword();
	}

	@Override
	public String getUsername() {
		return userDTO.getUsername();
	}
	
	public final UserDTO getUser() {
		return userDTO;
	}

}
