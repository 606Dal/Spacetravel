package com.spacetravel.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingDTO {
	
	private int totalData; //전체 데이터 개수
	private int startPage; //페이지 목록 버튼의 시작 번호
	private int endPage; //페이지 목록 버튼의 끝 번호
	private boolean prev; //이전 버튼을 나타내는 부울 값
	private boolean next; //다음 버튼을 나타내는 부울 값
	
	private int displayPageNum = 10;  // 페이지 버튼에 나타낼 페이지 번호의 개수
	
	private PageCriteriaDTO pageCriteriaDTO;
	private FindCriteriaDTO findCriteriaDTO;
	
	
	public void setPageCriteriaDTO(PageCriteriaDTO pageCriteriaDTO) {
		this.pageCriteriaDTO = pageCriteriaDTO;
	}
	
	public void setFindCriteriaDTO(FindCriteriaDTO findCriteriaDTO) {
		this.findCriteriaDTO = findCriteriaDTO;
	}
	
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		
		getPagingData();
	}
	
	private void getPagingData() {
		endPage = (int)(Math.ceil(pageCriteriaDTO.getPage()/(double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;

		int finalEndPage = (int)(Math.ceil(totalData/(double)pageCriteriaDTO.getNumPerPage()));
		
		if(endPage > finalEndPage) {
			endPage = finalEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage*pageCriteriaDTO.getNumPerPage() >= totalData ? false : true;
		
	}//getPagingData()

	//uri 생성
	public String makeURI(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", pageCriteriaDTO.getNumPerPage())
				.build();
			
		return uriComponents.toUriString();
	}
	
	public String makeFindURI(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", findCriteriaDTO.getNumPerPage())
				.queryParam("findType", findCriteriaDTO.getFindType())
				.queryParam("keyword", findCriteriaDTO.getKeyword())
				.build();
		uriComponents.encode();
			
		return uriComponents.toUriString();
	}
	
	// getter, setter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public PageCriteriaDTO getPageCriteriaDTO() {
		return pageCriteriaDTO;
	}
	
	public FindCriteriaDTO geFindCriteriaDTO() {
		return findCriteriaDTO;
	}

	public int getTotalData() {
		return totalData;
	}

	@Override
	public String toString() {
		return "PagingDTO [totalData=" + totalData + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
	
	

}
