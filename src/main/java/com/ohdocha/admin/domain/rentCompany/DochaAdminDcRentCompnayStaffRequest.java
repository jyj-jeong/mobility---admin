package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("dcRentCompanyStaffRequest")
public class DochaAdminDcRentCompnayStaffRequest extends CommonRequestDto {
	
	//DC_RENTCOMPANY_STAFF 담당자정보
	private String rtIdx					 ;
	private String rsIdx                     ; //제휴사직원idsx
	private String staffName                 ; //직원명
	private String staffContact1             ; //연락처1
	private String staffContact2             ; //연락처2
	private String staffEmail                ; //이메일
	private String staffTitle                ; //직위
	private String ownerYn                   ; //대표여부
	private String staffTypeCode             ; //직원분류code
	private String urIdx					 ; //직원
	private String delYn         			 ; //사용유무
	private String userRole					 ;
	private String regId					 ;
	private String modId					 ;
	
	public String getRsIdx() {
		return rsIdx;
	}
	public void setRsIdx(String rsIdx) {
		this.rsIdx = rsIdx;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffContact1() {
		return staffContact1;
	}
	public void setStaffContact1(String staffContact1) {
		this.staffContact1 = staffContact1;
	}
	public String getStaffContact2() {
		return staffContact2;
	}
	public void setStaffContact2(String staffContact2) {
		this.staffContact2 = staffContact2;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	public String getStaffTitle() {
		return staffTitle;
	}
	public void setStaffTitle(String staffTitle) {
		this.staffTitle = staffTitle;
	}
	public String getOwnerYn() {
		return ownerYn;
	}
	public void setOwnerYn(String ownerYn) {
		this.ownerYn = ownerYn;
	}
	public String getStaffTypeCode() {
		return staffTypeCode;
	}
	public void setStaffTypeCode(String staffTypeCode) {
		this.staffTypeCode = staffTypeCode;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUrIdx() {
		return urIdx;
	}
	public void setUrIdx(String urIdx) {
		this.urIdx = urIdx;
	}
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	

}
