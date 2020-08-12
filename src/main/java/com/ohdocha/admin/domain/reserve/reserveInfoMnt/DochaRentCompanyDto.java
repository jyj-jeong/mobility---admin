package com.ohdocha.admin.domain.reserve.reserveInfoMnt;

import org.apache.ibatis.type.Alias;

@Alias("RentCompanyDto")		
public class DochaRentCompanyDto {

	private String rtIdx;
	private String rtPIdx;
	private String companyName;
	private String company_zipcode;
	private String companyAddress;
	private String companyAddressDetail;
	private String lat;
	private String lng;
	private String establishedDate;
	private String companyRegistrationNumber;
	private String companyRegistrationImg;
	private String account_bank;
	private String accountNumber;
	private String accountHolder;
	private String accountImgIdx;
	private String longtermRentYn;
	private String shorttermRentYn;
	private String allianceStatus;
	private String branchAbleYn;
	private String carCount;
	private String etc;
	private String regId;
	
	private String regDt;
	private String modId;
	private String modDt;
	private String delYn;
	private String companyContact1;
	private String companyContact2;
	private String alarmYn;
	private String companyRegistrationName;
	private String branchName;
	private String accessYn;
	private String staffName;
	private String staffContact1;
	private String ownerYn;
	private String staffTitle;
	
	
	
	
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
	public String getOwnerYn() {
		return ownerYn;
	}
	public void setOwnerYn(String ownerYn) {
		this.ownerYn = ownerYn;
	}
	public String getStaffTitle() {
		return staffTitle;
	}
	public void setStaffTitle(String staffTitle) {
		this.staffTitle = staffTitle;
	}
	
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getRtPIdx() {
		return rtPIdx;
	}
	public void setRtPIdx(String rtPIdx) {
		this.rtPIdx = rtPIdx;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompany_zipcode() {
		return company_zipcode;
	}
	public void setCompany_zipcode(String company_zipcode) {
		this.company_zipcode = company_zipcode;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyAddressDetail() {
		return companyAddressDetail;
	}
	public void setCompanyAddressDetail(String companyAddressDetail) {
		this.companyAddressDetail = companyAddressDetail;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(String establishedDate) {
		this.establishedDate = establishedDate;
	}
	public String getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}
	public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
		this.companyRegistrationNumber = companyRegistrationNumber;
	}
	public String getCompanyRegistrationImg() {
		return companyRegistrationImg;
	}
	public void setCompanyRegistrationImg(String companyRegistrationImg) {
		this.companyRegistrationImg = companyRegistrationImg;
	}
	public String getAccount_bank() {
		return account_bank;
	}
	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getAccountImgIdx() {
		return accountImgIdx;
	}
	public void setAccountImgIdx(String accountImgIdx) {
		this.accountImgIdx = accountImgIdx;
	}
	public String getLongtermRentYn() {
		return longtermRentYn;
	}
	public void setLongtermRentYn(String longtermRentYn) {
		this.longtermRentYn = longtermRentYn;
	}
	public String getShorttermRentYn() {
		return shorttermRentYn;
	}
	public void setShorttermRentYn(String shorttermRentYn) {
		this.shorttermRentYn = shorttermRentYn;
	}
	public String getAllianceStatus() {
		return allianceStatus;
	}
	public void setAllianceStatus(String allianceStatus) {
		this.allianceStatus = allianceStatus;
	}
	public String getBranchAbleYn() {
		return branchAbleYn;
	}
	public void setBranchAbleYn(String branchAbleYn) {
		this.branchAbleYn = branchAbleYn;
	}
	public String getCarCount() {
		return carCount;
	}
	public void setCarCount(String carCount) {
		this.carCount = carCount;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
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
	public String getCompanyContact1() {
		return companyContact1;
	}
	public void setCompanyContact1(String companyContact1) {
		this.companyContact1 = companyContact1;
	}
	public String getCompanyContact2() {
		return companyContact2;
	}
	public void setCompanyContact2(String companyContact2) {
		this.companyContact2 = companyContact2;
	}
	public String getAlarmYn() {
		return alarmYn;
	}
	public void setAlarmYn(String alarmYn) {
		this.alarmYn = alarmYn;
	}
	public String getCompanyRegistrationName() {
		return companyRegistrationName;
	}
	public void setCompanyRegistrationName(String companyRegistrationName) {
		this.companyRegistrationName = companyRegistrationName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccessYn() {
		return accessYn;
	}
	public void setAccessYn(String accessYn) {
		this.accessYn = accessYn;
	}

	
	
}
