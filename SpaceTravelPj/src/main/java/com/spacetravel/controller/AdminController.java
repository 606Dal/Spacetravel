package com.spacetravel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.service.AdminService;
import com.spacetravel.service.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/adminPage")
	public void adminPage() {
		
	}
	
	// 유저 목록
	@GetMapping("/userList")
	public String userList(Model model) {
		
		int totalUser = adminService.getCountUser();
		model.addAttribute("totalUser", totalUser);
		
		List<UserDTO> userList = adminService.getUserList();
		model.addAttribute("userList", userList);
		
		return "admin/userList";
	}
	
	@PostMapping("deleteUserOk")
	public String deleteUserOk(HttpServletRequest request
						   , @AuthenticationPrincipal CustomUserDetails userDetails
						   , Model model) {
		// 관리자 확인, username으로 검색해서 삭제
		String userRole = userDetails.getUser().getRolename();
		String username = request.getParameter("username");
		
		try {
			if(userRole.equals("ADMIN")) {
				int isOk = adminService.deleteUser(username);
				
				if(isOk != 1) {
					model.addAttribute("msg", "유저 삭제를 실패하였습니다.");
					model.addAttribute("url", "/admin/userList");
				} else {
					// 성공 시
					model.addAttribute("msg", "유저가 삭제되었습니다.");
					model.addAttribute("url", "/admin/userList");
				}
				
				return "board/messageAlert";
			} else {
				model.addAttribute("msg", "권한이 없습니다.");
				model.addAttribute("url", "/admin/userList");
				
				return "board/messageAlert";
			}
		} catch (DataAccessException e) {
			
		} catch (Exception e) {
			log.info("유저 삭제 중 오류 발생");
		}
		
		return "admin/userList";
	}
	
}
