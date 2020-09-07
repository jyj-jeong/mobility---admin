package com.ohdocha.admin.domain.reserve.matchingService;


import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("matchingDetailRequest")	
public class DochaAdminMatchingDetailRequest extends CommonRequestDto {
	private String quoteUserId;
	private String quoteUserName;
	private String quoteUserContact;
	private String quoteUserGender;
	private String quoteuserBirthday;
	private String quoteStartDate;
	private String quoteEndDate;
	private String rentStartDate;
	private String rentEndDate;
	private String periodDt;
	private String quIdx;
	private String deliveryAddr;
	private String returnAddr;
	public String getQuoteUserId() {
		return quoteUserId;
	}
	public void setQuoteUserId(String quoteUserId) {
		this.quoteUserId = quoteUserId;
	}
	public String getQuoteUserName() {
		return quoteUserName;
	}
	public void setQuoteUserName(String quoteUserName) {
		this.quoteUserName = quoteUserName;
	}
	public String getQuoteUserContact() {
		return quoteUserContact;
	}
	public void setQuoteUserContact(String quoteUserContact) {
		this.quoteUserContact = quoteUserContact;
	}
	public String getQuoteUserGender() {
		return quoteUserGender;
	}
	public void setQuoteUserGender(String quoteUserGender) {
		this.quoteUserGender = quoteUserGender;
	}
	public String getQuoteuserBirthday() {
		return quoteuserBirthday;
	}
	public void setQuoteuserBirthday(String quoteuserBirthday) {
		this.quoteuserBirthday = quoteuserBirthday;
	}
	public String getQuoteStartDate() {
		return quoteStartDate;
	}
	public void setQuoteStartDate(String quoteStartDate) {
		this.quoteStartDate = quoteStartDate;
	}
	public String getQuoteEndDate() {
		return quoteEndDate;
	}
	public void setQuoteEndDate(String quoteEndDate) {
		this.quoteEndDate = quoteEndDate;
	}
	public String getRentStartDate() {
		return rentStartDate;
	}
	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}
	public String getRentEndDate() {
		return rentEndDate;
	}
	public void setRentEndDate(String rentEndDate) {
		this.rentEndDate = rentEndDate;
	}
	public String getPeriodDt() {
		return periodDt;
	}
	public void setPeriodDt(String periodDt) {
		this.periodDt = periodDt;
	}
	public String getQuIdx() {
		return quIdx;
	}
	public void setQuIdx(String quIdx) {
		this.quIdx = quIdx;
	}
	public String getDeliveryAddr() {
		return deliveryAddr;
	}
	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}
	public String getReturnAddr() {
		return returnAddr;
	}
	public void setReturnAddr(String returnAddr) {
		this.returnAddr = returnAddr;
	}

}
