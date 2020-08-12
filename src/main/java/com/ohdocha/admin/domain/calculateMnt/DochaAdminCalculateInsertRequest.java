package com.ohdocha.admin.domain.calculateMnt;

import lombok.Data;

@Data
public class DochaAdminCalculateInsertRequest extends DochaAdminCalculateListRequest{
	
	DochaAdminCalculateInsertRequest() {
		
	}
	
	private static final long serialVersionUID = 1L;
	
    private String rtIdx;
    private String rmIdx;
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getRmIdx() {
		return rmIdx;
	}
	public void setRmIdx(String rmIdx) {
		this.rmIdx = rmIdx;
	}
    
}
