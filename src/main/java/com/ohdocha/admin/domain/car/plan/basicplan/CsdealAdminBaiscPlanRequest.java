package com.ohdocha.admin.domain.car.plan.basicplan;

import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("basicPlanRequest")
public class CsdealAdminBaiscPlanRequest extends CommonRequestDto {
	
	private static final long serialVersionUID = 1L;
	
	private String rtIdx;		//회원사 idx
	private String userRole;		//회원사 idx

	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	

}
