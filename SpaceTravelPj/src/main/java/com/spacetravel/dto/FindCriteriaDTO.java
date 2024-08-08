package com.spacetravel.dto;

import jakarta.validation.constraints.Size;

public class FindCriteriaDTO extends PageCriteriaDTO {

	private String findType;
	
	@Size(max = 50)
	private String keyword;
	
	public String getFindType() {
		return findType;
	}
	public void setFindType(String findType) {
		this.findType = findType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "findCriteriaDTO [findType=" + findType + ", keyword=" + keyword + "]";
	}
	
}
