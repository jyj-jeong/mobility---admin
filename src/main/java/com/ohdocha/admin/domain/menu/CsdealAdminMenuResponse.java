package com.ohdocha.admin.domain.menu;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("menuInfoResponse")
public class CsdealAdminMenuResponse {
	
	private String menuCd;
	private String menuNm;
	private String menuUrl;
	private String upperMenuCd;
	private String menuOrder;
	private String menuLevel;
	private String menuDc;
	private String regDt;
	private String useYn;
	private String newYn;
	private String readYn;
	private String delYn;
	private String saveYn;
	private String excelDownYn;
	private String flag;
	
	private String depthFullname;
	
	private String templateCd;
	
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getUpperMenuCd() {
		return upperMenuCd;
	}
	public void setUpperMenuCd(String upperMenuCd) {
		this.upperMenuCd = upperMenuCd;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuDc() {
		return menuDc;
	}
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getDepthFullname() {
		return depthFullname;
	}
	public void setDepthFullname(String depthFullname) {
		this.depthFullname = depthFullname;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	
	
	
}
