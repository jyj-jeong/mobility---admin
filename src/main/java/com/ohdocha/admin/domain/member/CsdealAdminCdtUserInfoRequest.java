package com.ohdocha.admin.domain.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cdtUserInfoRequest")
public class CsdealAdminCdtUserInfoRequest {
	
	
    private String userId;						// 회원ID
    private String userPassword;				// 비밀번호
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

    
    
}
