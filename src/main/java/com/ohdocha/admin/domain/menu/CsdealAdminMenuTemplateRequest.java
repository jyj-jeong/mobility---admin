package com.ohdocha.admin.domain.menu;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("adminMenuTemplateRequest")
public class CsdealAdminMenuTemplateRequest {
	private String templateCd	;           //템플릿코드
	private String templateNm	;           //템플릿명
	private String templateMemo	;         	//템플릿설명
	private String regDt		;           //등록일
	private String regId		;           //등록자
	private String modDt		;           //수정일
	private String modId		;           //수정자
	
	public String getTemplateCd() {
		return templateCd;
	}
	public void setTemplateCd(String templateCd) {
		this.templateCd = templateCd;
	}
	public String getTemplateNm() {
		return templateNm;
	}
	public void setTemplateNm(String templateNm) {
		this.templateNm = templateNm;
	}
	public String getTemplateMemo() {
		return templateMemo;
	}
	public void setTemplateMemo(String templateMemo) {
		this.templateMemo = templateMemo;
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
	
	
	
}
