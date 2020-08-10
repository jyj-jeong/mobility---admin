package com.ohdocha.admin.domain.common;

import com.ohdocha.admin.domain.Criteria;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("commonRequestDto")
public class CommonRequestDto extends Criteria {
	
    private String currentUrl;
    private String previousUrl;
    private String searchType;
    private String searchKeyWord;
    

    
    
	public String getCurrentUrl() {
		return currentUrl;
	}
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	public String getPreviousUrl() {
		return previousUrl;
	}
	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
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