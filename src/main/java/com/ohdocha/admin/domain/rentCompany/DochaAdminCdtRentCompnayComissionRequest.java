package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cdtRentCompanyComissionRequest")
public class DochaAdminCdtRentCompnayComissionRequest extends CommonRequestDto {
	
	private String rtIdx         ; //제휴사idx
	private String commissionPer ; //수수료윯
	private String taxInvoiceCode; //세금계산서 발행주체
	private String regId         ; //등록일
	private String regDt         ; //등록일시
	private String modId         ; //수정자
	private String modDt         ; //수정일시
	private String delYn         ; //사용유무
	
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
