package com.spacetravel.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z가-힣0-9]{4,20}", message = "아이디는 4~20자, 한글, 영문자, 숫자만 가능합니다.")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,20}", 
			 message = "비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요.")
	private String password;
	
	private LocalDateTime regdate;
	private LocalDateTime updateDate;
	private String rolename;
	
	// getter, setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getRegdate() {
		return regdate;
	}
	public void setRegdate(LocalDateTime regdate) {
		ZoneId utcZone = ZoneId.of("UTC");
        ZoneId koreaZone = ZoneId.of("Asia/Seoul");
        
        ZonedDateTime utcTime = regdate.atZone(utcZone);
        ZonedDateTime koreaTime = utcTime.withZoneSameInstant(koreaZone);
        
		this.regdate = koreaTime.toLocalDateTime();
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		ZoneId utcZone = ZoneId.of("UTC");
        ZoneId koreaZone = ZoneId.of("Asia/Seoul");
        
        ZonedDateTime utcTime = updateDate.atZone(utcZone);
        ZonedDateTime koreaTime = utcTime.withZoneSameInstant(koreaZone);
        
		this.updateDate = koreaTime.toLocalDateTime();
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", regdate=" + regdate + ", updateDate="
				+ updateDate + "]";
	}
	
}
