package com.ohdocha.admin.domain.reserve.payment;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("reserveInfoRequest")						   	
public class DochaAdminPaymentInfoRequest  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private int page;				//시작페이지
    private int displayPageNum;		//몇개를 보여줄 것인가
    private int totalRowCount; 		//총 row 갯수
    private String rmIdx;
    private String userId;
    private String rtIdx;
    private String userRole;
    private String crIdx;
    
    //CDT_PAYMENT_DETAIL
    private String pdIdx;
    private String urIdx;
    private String pgCode;
    private String paymentTypeCode;
    private String paymentKindCode;
    private String paymentAmount;
    private String approvalNumber;
    private String paymentDate;
    private String etc;

    //CDT_RESERVE_LOG
    
    private String reIdx;
	private String reserveStatusCode;
	private String rentStartDay;
	private String rentStartTime;
	private String rentEndDay;
	private String rentEndTime;
	private String rentFee;
	private String insuranceFee;
	private String carDeposit;
	private String discountFee;
	private String cancelFee;
	private String cancelAmount;
	private String reserveEtc;
	private String regId;
	
	
    
    private String searchKeyWord;
    private String gbnStatus;
    private String gbnDay;
    private String gbnLocation;
    private String gbnReserve;
    private String gbnInput;
    private String gbnCarType;
    
    
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public String getRmIdx() {
		return rmIdx;
	}
	public void setRmIdx(String rmIdx) {
		this.rmIdx = rmIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getCrIdx() {
		return crIdx;
	}
	public void setCrIdx(String crIdx) {
		this.crIdx = crIdx;
	}
	public String getPdIdx() {
		return pdIdx;
	}
	public void setPdIdx(String pdIdx) {
		this.pdIdx = pdIdx;
	}
	public String getUrIdx() {
		return urIdx;
	}
	public void setUrIdx(String urIdx) {
		this.urIdx = urIdx;
	}
	public String getPgCode() {
		return pgCode;
	}
	public void setPgCode(String pgCode) {
		this.pgCode = pgCode;
	}
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}
	public String getPaymentKindCode() {
		return paymentKindCode;
	}
	public void setPaymentKindCode(String paymentKindCode) {
		this.paymentKindCode = paymentKindCode;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getReIdx() {
		return reIdx;
	}
	public void setReIdx(String reIdx) {
		this.reIdx = reIdx;
	}
	public String getReserveStatusCode() {
		return reserveStatusCode;
	}
	public void setReserveStatusCode(String reserveStatusCode) {
		this.reserveStatusCode = reserveStatusCode;
	}
	public String getRentStartDay() {
		return rentStartDay;
	}
	public void setRentStartDay(String rentStartDay) {
		this.rentStartDay = rentStartDay;
	}
	public String getRentStartTime() {
		return rentStartTime;
	}
	public void setRentStartTime(String rentStartTime) {
		this.rentStartTime = rentStartTime;
	}
	public String getRentEndDay() {
		return rentEndDay;
	}
	public void setRentEndDay(String rentEndDay) {
		this.rentEndDay = rentEndDay;
	}
	public String getRentEndTime() {
		return rentEndTime;
	}
	public void setRentEndTime(String rentEndTime) {
		this.rentEndTime = rentEndTime;
	}
	public String getRentFee() {
		return rentFee;
	}
	public void setRentFee(String rentFee) {
		this.rentFee = rentFee;
	}
	public String getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(String insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public String getCarDeposit() {
		return carDeposit;
	}
	public void setCarDeposit(String carDeposit) {
		this.carDeposit = carDeposit;
	}
	public String getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}
	public String getCancelFee() {
		return cancelFee;
	}
	public void setCancelFee(String cancelFee) {
		this.cancelFee = cancelFee;
	}
	public String getCancelAmount() {
		return cancelAmount;
	}
	public void setCancelAmount(String cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	public String getReserveEtc() {
		return reserveEtc;
	}
	public void setReserveEtc(String reserveEtc) {
		this.reserveEtc = reserveEtc;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public String getGbnStatus() {
		return gbnStatus;
	}
	public void setGbnStatus(String gbnStatus) {
		this.gbnStatus = gbnStatus;
	}
	public String getGbnDay() {
		return gbnDay;
	}
	public void setGbnDay(String gbnDay) {
		this.gbnDay = gbnDay;
	}
	public String getGbnLocation() {
		return gbnLocation;
	}
	public void setGbnLocation(String gbnLocation) {
		this.gbnLocation = gbnLocation;
	}
	public String getGbnReserve() {
		return gbnReserve;
	}
	public void setGbnReserve(String gbnReserve) {
		this.gbnReserve = gbnReserve;
	}
	public String getGbnInput() {
		return gbnInput;
	}
	public void setGbnInput(String gbnInput) {
		this.gbnInput = gbnInput;
	}
	public String getGbnCarType() {
		return gbnCarType;
	}
	public void setGbnCarType(String gbnCarType) {
		this.gbnCarType = gbnCarType;
	}
    
	
    
    
    
}
