package com.ohdocha.admin.domain.menu;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("menuInfoRequest")
public class DochaAdminMenuRequest {
	
	private String urIdx;
	private String newYn;
	private String readYn;
	private String delYn;
	private String saveYn;
	private String excelDownYn;
	private String regDt;
	private String regId;
	private String modDt;
	private String modId;
	
	private String templateCd;
	
	private String userRole;
	
	public String getUrIdx() {
		return urIdx;
	}
	public void setUrIdx(String urIdx) {
		this.urIdx = urIdx;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getTemplateCd() {
		return templateCd;
	}
	public void setTemplateCd(String templateCd) {
		this.templateCd = templateCd;
	}
	public String getNewYn() {
		return newYn;
	}
	public void setNewYn(String newYn) {
		this.newYn = newYn;
	}
	public String getReadYn() {
		return readYn;
	}
	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getSaveYn() {
		return saveYn;
	}
	public void setSaveYn(String saveYn) {
		this.saveYn = saveYn;
	}
	public String getExcelDownYn() {
		return excelDownYn;
	}
	public void setExcelDownYn(String excelDownYn) {
		this.excelDownYn = excelDownYn;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
}
