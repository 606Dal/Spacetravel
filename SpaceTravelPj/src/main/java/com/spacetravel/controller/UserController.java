package com.spacetravel.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Controller
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/login")
	public String loginPage() {
		return "user/loginPage";
	}
	
	// 회원 가입
	@GetMapping("/singUp")
	public String singUpForm() {
		
		return "user/singUpForm";
	}
	  // 유저 등록
	@PostMapping("/singUpOk")
	public String singUpOk(@Valid UserDTO userDTO,
						   @RequestParam(value = "username") String username,
						   Model model) {
		try {
			String result = userService.usernameDuplicateCheck(username);
			// DB에서 검색된 아이디가 없으면
			if(result == null) {
				userService.insertUser(userDTO);
				
				model.addAttribute("msg", "회원 가입에 성공하였습니다.");
				model.addAttribute("url", "user/loginPage");
				
				return "board/messageAlert";
			} else {
				model.addAttribute("msg", "중복된 아이디 입니다.");
				model.addAttribute("reUsername", username);
			}
		} catch (Exception e) {
			log.warn("회원가입 중 오류 발생");
		}
		return "user/singUpForm";
	}
	
	// 비밀번호 변경 페이지
	@GetMapping("/changePasswordForm")
	public void changePasswordForm() {
	}
	
	@PostMapping("/changePasswordOk")
	public String changePasswordOk(UserDTO userDTO
								 , @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,20}", 
										message = "비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요.") 
								   @RequestParam("newPassword") String newPassword
								 , HttpServletRequest request
								 , Authentication authentication
								 , Model model) {
		try {
			String username = authentication.getName();
			String currentPassword = request.getParameter("currentPassword");
			
			// 폼에서 입력받은 현재 비밀번호와 기존의 비밀번호와 맞는지 확인
			boolean matchesPassword = userService.isMatchesPassword(username, currentPassword);
			// 동일하면
			if(matchesPassword == true) {
				userDTO.setUsername(username);
				userDTO.setPassword(newPassword);
				
				userService.updatePassword(userDTO);
				
				model.addAttribute("msg", "비밀번호 변경에 성공하였습니다.");
				model.addAttribute("url", "/user/logout");
					
				return "board/messageAlert";
				
			} else {
				model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해 주세요");
			}
		} catch (Exception e) {
			log.warn("비밀번호 변경 중 오류 발생");
		}
		return "user/changePasswordForm";
	}
	
	// 계정 관리 페이지
	@GetMapping("/myAccountPage")
	public void myAccountPage(Model model
							, Authentication authentication) {
		
		String username = authentication.getName();
		Date singUpDate = userService.findUserDate(username);
		LocalDateTime localDateTime = singUpDate.toInstant()
				.atZone(ZoneId.of("Asia/Seoul"))
				.toLocalDateTime();
		
		model.addAttribute("singUpDate", localDateTime);
	}
	
	// 계정 관리 - 탈퇴 페이지
	@GetMapping("/accountPage")
	public void accountPage() {
	}
		// 계정 탈퇴
	@PostMapping("/deleteAccountOk")
	public String deleteAccountOk(HttpServletRequest request
								, Authentication authentication
								, Model model) {
		try {
			String username = authentication.getName();
			String password = request.getParameter("password");
			
			// 폼에서 입력받은 비밀번호가 기존의 비밀번호와 맞는지 확인
			boolean matchesPassword = userService.isMatchesPassword(username, password);
			// 동일하면
			if(matchesPassword == true) {
				int isOk = userService.deleteAccount(username);
				
				// 회원 탈퇴 실패 시
				if(isOk != 1) {
					model.addAttribute("msg", "계정 삭제를 실패하였습니다.");
					model.addAttribute("url", "/user/accountPage");
				} else {
					// 성공 시
					model.addAttribute("msg", "계정 정보가 삭제되었습니다.");
					model.addAttribute("url", "/user/logout");
				}
				return "board/messageAlert";
			} else {
				model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해 주세요");
			}
		} catch (DataAccessException e) {
			log.warn("계정 삭제 중 오류 발생"+e.getMessage());
		} catch (Exception e) {
			log.warn("계정 삭제 중 오류 발생");
		}
		return "user/accountPage";
	}
	
}
