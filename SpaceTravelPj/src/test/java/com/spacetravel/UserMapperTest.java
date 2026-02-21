package com.spacetravel;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.UserMapper;
import com.spacetravel.service.AdminService;
import com.spacetravel.service.AuthenticationProviderService;
import com.spacetravel.service.UserService;

@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AdminService adminService;
	
	private static Logger log = LoggerFactory.getLogger(UserMapperTest.class);

	@Test
	@Disabled
	public void testInsert() {

		UserDTO uDto = new UserDTO();
		
		uDto.setUsername("테스터2");
		uDto.setPassword("12345");
		
		userService.insertUser(uDto);
		
		System.out.println("테스트 유저 추가 성공" + uDto.toString());
	}
	
	@Test
	@Disabled
	public void testFindUser() {
		log.info(userMapper.findByUsername("관리자").toString());
	}
	
	@Test
	@Disabled
	public void testIdCheck() {
		log.info(userMapper.usernameDuplicateCheck("테스터2").toString());
		String result = userService.usernameDuplicateCheck("관리");
		if(result == null) {
			System.out.println("null입니다");
		}
	}
	
	@Test
	@Disabled
	public void getCountUser() {
		System.out.println("총 유저 수 : "+adminService.getCountUser());
	}

	@Test
	@Disabled
	public void userList() {
		List<UserDTO> userList = adminService.getUserList();
		System.out.println("List 회원 출력 1 : "+userList.get(0).toString());
	}
	
	@Test
	@Disabled
	public void deleteUserTest() {
	    String username = "테스터3";

	    int result = userMapper.deleteUser(username);

	    assertEquals(1, result);
	}
	
	@Test
	@Disabled
	public void updateStatusLockedTest() {
	    UserDTO dto = new UserDTO();
	    dto.setUsername("테스터3");
	    dto.setStatus(UserDTO.STATUS_LOCKED);

	    int result = userMapper.updateStatus(dto);

	    assertEquals(1, result);
	}
	
	@Test
//	@Disabled
	public void updateStatusActiveTest() {
	    UserDTO dto = new UserDTO();
	    dto.setUsername("테스터3");
	    dto.setStatus(UserDTO.STATUS_ACTIVE);

	    int result = userMapper.updateStatus(dto);

	    assertEquals(1, result);
	}
}
