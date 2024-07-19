package com.spacetravel.dto;

public class AuthorityDTO {

	private int id;
	private String rolename; // 권한의 이름
	
	// getter, setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Override
	public String toString() {
		return "AuthorityDTO [id=" + id + ", rolename=" + rolename + "]";
	}
	
}
