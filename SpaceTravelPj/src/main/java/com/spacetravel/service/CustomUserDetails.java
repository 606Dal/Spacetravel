package com.spacetravel.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spacetravel.dto.UserDTO;
/*
 * 스프링 시큐리티 인증 구현용
 */
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final UserDTO userDTO;
	
	public CustomUserDetails(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	// 해당 유저의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+userDTO.getRolename();}); 
		
		return collectors;
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
	
	//계정이 만료되지 않았는지 리턴한다. (true : 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//정지된 유저가 아닌지 확인
	@Override
	public boolean isAccountNonLocked() {
		return !UserDTO.STATUS_LOCKED.equals(userDTO.getStatus());
	}

	//비밀번호가 만료되지 않았는지 리턴한다. (true : 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화(사용가능)인지 리턴한다.
	@Override
	public boolean isEnabled() {
		return !UserDTO.STATUS_DELETED.equals(userDTO.getStatus());
	}

}
