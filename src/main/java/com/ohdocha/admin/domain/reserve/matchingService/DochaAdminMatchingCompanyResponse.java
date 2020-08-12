package com.ohdocha.admin.domain.reserve.matchingService;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("matchingCompanyResponse")
public class DochaAdminMatchingCompanyResponse extends CommonResponseDto {

	private String qrIdx;
	private String quIdx;
	private String companyName;
	private String staffName;
	private String staffContact1;
	private String carNumber;
	private String fuelName;
	private String carRegDt;
	private String colorName;
	private String mileage;
	private String personalCover;
	private String propertyDamageCover;
	private String onselfDamageCover;
	private String insuranceFee;
	private String carDamageCover;
	private String rentFee;
	private String deliveryStandardPay;
	private String paymentAmount;
	private String caroptionListValue;
	private String regDt;
	private String rentStartDate;

	public String getQrIdx() {
		return qrIdx;
	}
	public void setQrIdx(String qrIdx) {
		this.qrIdx = qrIdx;
	}
	public String getQuIdx() {
		return quIdx;
	}
	public void setQuIdx(String quIdx) {
		this.quIdx = quIdx;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public String getCarRegDt() {
		return carRegDt;
	}
	public void setCarRegDt(String carRegDt) {
		this.carRegDt = carRegDt;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getPersonalCover() {
		return personalCover;
	}
	public void setPersonalCover(String personalCover) {
		this.personalCover = personalCover;
	}
	public String getPropertyDamageCover() {
		return propertyDamageCover;
	}
	public void setPropertyDamageCover(String propertyDamageCover) {
		this.propertyDamageCover = propertyDamageCover;
	}
	public String getOnselfDamageCover() {
		return onselfDamageCover;
	}
	public void setOnselfDamageCover(String onselfDamageCover) {
		this.onselfDamageCover = onselfDamageCover;
	}
	public String getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(String insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public String getCarDamageCover() {
		return carDamageCover;
	}
	public void setCarDamageCover(String carDamageCover) {
		this.carDamageCover = carDamageCover;
	}
	public String getRentFee() {
		return rentFee;
	}
	public void setRentFee(String rentFee) {
		this.rentFee = rentFee;
	}
	public String getDeliveryStandardPay() {
		return deliveryStandardPay;
	}
	public void setDeliveryStandardPay(String deliveryStandardPay) {
		this.deliveryStandardPay = deliveryStandardPay;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getCaroptionListValue() {
		return caroptionListValue;
	}
	public void setCaroptionListValue(String caroptionListValue) {
		this.caroptionListValue = caroptionListValue;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRentStartDate() {
		return rentStartDate;
	}
	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}
	
	
}
