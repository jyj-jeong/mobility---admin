package com.ohdocha.admin.domain.calculateMnt;

import com.ohdocha.admin.domain.calculateMnt.DochaAdminCalculateListRequest;
import lombok.Data;


@Data
public class DochaAdminDateCalculateListRequest extends DochaAdminCalculateListRequest {
	
	DochaAdminDateCalculateListRequest() {
		
	}
	
	private static final long serialVersionUID = 1L;
	
//	@Pattern(regexp = "^([1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message="죄회하려는 정산일자가 날짜표현식에 어긋납니다")
//    @NotBlank(message = "정산일자는 필수값입니다")
    private String accountExpDt;


	public String getAccountExpDt() {
		return accountExpDt;
	}


	public void setAccountExpDt(String accountExpDt) {
		this.accountExpDt = accountExpDt;
	}
    
    

    
}
