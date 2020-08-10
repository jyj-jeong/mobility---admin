package com.ohdocha.admin.domain.car.plan.insuranceTemplate;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("insuranceTemplateRequest")
public class CsdealAdminInsuranceTemplateRequest {
	
	private static final long serialVersionUID = 1L;
	
	private String rtIdx;		//회원사 idx
	private String userRole;	//회원사 권한
	private String ciIdx;		//보험료템플릿 idx

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
	public String getCiIdx() {
		return ciIdx;
	}
	public void setCiIdx(String ciIdx) {
		this.ciIdx = ciIdx;
	}
}
