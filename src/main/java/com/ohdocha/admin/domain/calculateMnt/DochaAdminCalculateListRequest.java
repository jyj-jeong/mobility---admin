package com.ohdocha.admin.domain.calculateMnt;

import lombok.Data;

import java.io.Serializable;

@Data
public class DochaAdminCalculateListRequest  implements Serializable {
	
	DochaAdminCalculateListRequest() {
		
	}
	
	private static final long serialVersionUID = 1L;
	
	//페이징 파라미터
    private int page;				//시작페이지
    private int displayPageNum;		//몇개를 보여줄 것인가
    private int totalRowCount; 		//총 row 갯수

    //search 파라미터
    private String searchStartDt;
    private String searchEndDt;
    private String searchType;
    private String searchKeyWord;
  	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public String getSearchStartDt() {
		return searchStartDt;
	}
	public void setSearchStartDt(String searchStartDt) {
		this.searchStartDt = searchStartDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}    
    
    
}
