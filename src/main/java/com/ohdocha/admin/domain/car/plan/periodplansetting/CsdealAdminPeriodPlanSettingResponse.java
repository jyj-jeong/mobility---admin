package com.ohdocha.admin.domain.car.plan.periodplansetting;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("periodPlanResponse")
public class CsdealAdminPeriodPlanSettingResponse extends CommonResponseDto {
	
	private String companyName 				; // 회사명
	private String branchName 				; // 지점명
	private String perIdx                   ; // 기간요금idx
	private String crIdx                    ; // 차량idx
	private String rtIdx                    ; // 회원사idx
	private String periodStartDt            ; // 기간시작일
	private String periodEndDt              ; // 기간종료일
	private String discountExtrachargeCode  ; // 할인할증구분
	private String periodPay                ; // 기간요금
	private String periodEtc                ; // 내용
	private String regId                    ; // 등록자
	private String regDt                    ; // 등록일시
	private String modId                    ; // 수정자
	private String modDt                    ; // 수정일시
	private String delYn				    ; // 삭제여부
	private int settingCarCnt				; //설정 차량 갯수
	
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
	public String getPerIdx() {
		return perIdx;
	}
	public void setPerIdx(String perIdx) {
		this.perIdx = perIdx;
	}
	public String getCrIdx() {
		return crIdx;
	}
	public void setCrIdx(String crIdx) {
		this.crIdx = crIdx;
	}
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getPeriodStartDt() {
		return periodStartDt;
	}
	public void setPeriodStartDt(String periodStartDt) {
		this.periodStartDt = periodStartDt;
	}
	public String getPeriodEndDt() {
		return periodEndDt;
	}
	public void setPeriodEndDt(String periodEndDt) {
		this.periodEndDt = periodEndDt;
	}
	public String getDiscountExtrachargeCode() {
		return discountExtrachargeCode;
	}
	public void setDiscountExtrachargeCode(String discountExtrachargeCode) {
		this.discountExtrachargeCode = discountExtrachargeCode;
	}
	public String getPeriodPay() {
		return periodPay;
	}
	public void setPeriodPay(String periodPay) {
		this.periodPay = periodPay;
	}
	public String getPeriodEtc() {
		return periodEtc;
	}
	public void setPeriodEtc(String periodEtc) {
		this.periodEtc = periodEtc;
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
	public int getSettingCarCnt() {
		return settingCarCnt;
	}
	public void setSettingCarCnt(int settingCarCnt) {
		this.settingCarCnt = settingCarCnt;
	}
	
	
}
