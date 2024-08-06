package com.spacetravel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;

@Service
public interface AdminService {

	public int getCountUser();

	public List<UserDTO> getUserList();

	public int deleteUser(String username);

}
