package com.ohdocha.admin.domain;

public class PageMaker {

	private Criteria cri; //inside page, perPageNum 

	private int totalCount; // 전체 게시글 수
	private int startPage = 1; // 게시글 번호에 따른 (보여지는)페이지의 시작 번호
	private int endPage = 1; // 게시글 번호에 따른 (보여지는)페이지의 마지막 번호
	private boolean prev; // 이전 버튼을 누를 수 있는 경우/없는 경우 분류를 위함
	private boolean next;

	
	private int tempEndPage = 1;

	///////////////////////////////////////////////////////////////////////////////////////////////
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData(); // 전체 필드 변수들 세팅 : 전체 게시글 수의 setter가 호출될 때 전체 세팅되도록 함
	}

	private void calcData() { // 전체 필드 변수 값들을 계산하는 메서드
		
		
		if(cri.getDisplayPageNum() == 0) {
			cri.setDisplayPageNum(15);
		} else {
			cri.setDisplayPageNum(cri.getDisplayPageNum());
		}

		endPage = (int) (Math.ceil(cri.getPage() / (double) cri.getDisplayPageNum()) * cri.getDisplayPageNum());

		startPage = (endPage - cri.getDisplayPageNum()) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		this.tempEndPage = tempEndPage;

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		//만약에 displaypageNum이 총 개시물 수보다 크다면

		prev = startPage == 1 ? false : true; // 1페이지면 이전 누를 수 없게 false
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	}

	// getter setter

	public Criteria getCri() {
		return cri;
	}

	public int getTempEndPage() {
		return tempEndPage;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

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

	public void setTempEndPage(int tempEndPage) {
		this.tempEndPage = tempEndPage;
	}

	
}

