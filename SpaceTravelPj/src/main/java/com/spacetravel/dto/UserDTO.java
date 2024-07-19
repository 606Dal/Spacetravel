package com.spacetravel.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
	
	@NotBlank
	@Size(min = 3, max = 20, message = "아이디는 4~20자리로 입력해 주세요.")
	private String username;
	
	@NotBlank
	@Size(min = 4, max = 20, message = "비밀번호는 4~20자리로 입력해 주세요.")
	private String password;
	
	private Date regdate;
	private Date updateDate;
	private int roleId;
	
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", regdate=" + regdate + ", updateDate="
				+ updateDate + ", roleId=" + roleId + "]";
	}
	
}
