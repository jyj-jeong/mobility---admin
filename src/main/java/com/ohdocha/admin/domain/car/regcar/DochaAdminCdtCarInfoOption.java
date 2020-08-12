package com.ohdocha.admin.domain.car.regcar;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cdtCarInfoOption")
public class DochaAdminCdtCarInfoOption {
	
	private String rtIdx			;
	private String commissionPer    ;
	private String taxInvoiceCode   ;
	private String regId            ;
	private String regDt            ;
	private String modId            ;
	private String modDt            ;
	private String delYn            ;
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getCommissionPer() {
		return commissionPer;
	}
	public void setCommissionPer(String commissionPer) {
		this.commissionPer = commissionPer;
	}
	public String getTaxInvoiceCode() {
		return taxInvoiceCode;
	}
	public void setTaxInvoiceCode(String taxInvoiceCode) {
		this.taxInvoiceCode = taxInvoiceCode;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
}
