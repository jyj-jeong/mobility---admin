package com.ohdocha.admin.domain.car.regcar;

import com.ohdocha.admin.domain.common.CommonRequestDto;

public class DochaAdminRegCarRequest extends CommonRequestDto {
	
	private static final long serialVersionUID = 1L;
	
    private String rtIdx;			//회원사 idx
    private String companyName      ;
    private String branchName       ;
    private String modelName        ;
    private String carNumber        ;
    private String reserveStatus    ;
    
    private String userRole			;
    
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}	


	
}
