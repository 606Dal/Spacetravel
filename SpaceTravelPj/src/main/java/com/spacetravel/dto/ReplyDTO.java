package com.spacetravel.dto;

import java.sql.Date;

import jakarta.validation.constraints.Size;

public class ReplyDTO {

	private Integer replyid;
	private Integer id;
	
	@Size(min = 1, max = 1000)
	private String replyContent;
	
	private String replier;
	private Date regdate;
	private Date updatedate;
	
	// getter, setter
	public Integer getReplyid() {
		return replyid;
	}
	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplier() {
		return replier;
	}
	public void setReplier(String replier) {
		this.replier = replier;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "ReplyDTO [replyid=" + replyid + ", id=" + id + ", replyContent=" + replyContent + ", replier="
				+ replier + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
}
