package com.spacetravel.dto;

public class PageCriteriaDTO {

	private int page;
	private int numPerPage; //페이지당 글 개수
	
	public PageCriteriaDTO() {
		this.page = 1;
		this.numPerPage = 10;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getNumPerPage() {
		return this.numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		if (numPerPage <= 0 || numPerPage > 100) {
			this.numPerPage = 10;
			return;
		}
		this.numPerPage = numPerPage;
	}
	
	//시작 페이지 위치
	public int getStartPage() {
		return (this.page-1)*numPerPage;
	}

	@Override
	public String toString() {
		return "PageCriteria [page=" + page + ", numPerPage=" + numPerPage + "]";
	}
}
