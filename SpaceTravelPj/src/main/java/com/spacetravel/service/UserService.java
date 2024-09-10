package com.spacetravel.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;

@Service
public interface UserService {

	public void insertUser(UserDTO userDTO);
	
	public String usernameDuplicateCheck(String u);
	
	public boolean isMatchesPassword(String u, String currentPassword);
	
	public void updatePassword(UserDTO userDTO);

	public int deleteAccount(String u);

	public Date findUserDate(String u);
	
}
