package com.spacetravel.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class BoardDTO {
	
	private int id;
	
	@NotEmpty(message = "제목을 입력해 주세요.")
	@Size(max = 50)
	private String subject;
	
	@NotEmpty(message = "내용을 입력해 주세요.")
	private String content;
	
	@NotBlank
	@Size(min = 4, max = 20, message = "아이디는 4~20자리로 입력해 주세요.")
	private String writer;
	
	private Date regdate;
	
	@PositiveOrZero
	private int hit;
	
	
	// getter, setter
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [id=" + id + ", subject=" + subject + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}
	
}
