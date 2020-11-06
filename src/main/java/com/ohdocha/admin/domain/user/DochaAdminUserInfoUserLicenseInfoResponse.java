package com.ohdocha.admin.domain.user;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userLicenseInfoResponse")
public class DochaAdminUserInfoUserLicenseInfoResponse extends CommonResponseDto {
	
    private String ulIdx             ;          // 회원iDX
    private String licenseNumber     ;          // 면허번호
    private String licenseCode       ;          // 면허종류CODE
    private String licenseLocation   ;          // 면허지역
    private String licenseExpiration ;          // 갱신기간
    private String licenseIssueDt    ;           // 발급일
    private String licenseOwnerName  ;          // 면허소유자명
	private String licenseNumber2    ;          // 면허지역
	private String licenseImgName  ;          // 면허사진
	private String licenseUserBirthday;         // 생년월일
	private String licenseUserName   ;          // 회원이름

	public String getUlIdx() {
		return ulIdx;
	}
	public void setUlIdx(String ulIdx) {
		this.ulIdx = ulIdx;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getLicenseLocation() {
		return licenseLocation;
	}
	public void setLicenseLocation(String licenseLocation) {
		this.licenseLocation = licenseLocation;
	}
	public String getLicenseExpiration() {
		return licenseExpiration;
	}
	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}
	public String getLicenseIssueDt() {
		return licenseIssueDt;
	}
	public void setLicenseIssueDt(String licenseIssueDt) {
		this.licenseIssueDt = licenseIssueDt;
	}
	public String getLicenseOwnerName() {
		return licenseOwnerName;
	}
	public void setLicenseOwnerName(String licenseOwnerName) {
		this.licenseOwnerName = licenseOwnerName;
	}
	public String getLicenseNumber2() {
		return licenseNumber2;
	}
	public void setLicenseNumber2(String licenseNumber2) {
		this.licenseNumber2 = licenseNumber2;
	}
	public String getLicenseUserBirthday() {
		return licenseUserBirthday;
	}
	public void setLicenseUserBirthday(String licenseUserBirthday) {
		this.licenseUserBirthday = licenseUserBirthday;
	}
	public String getLicenseUserName() {
		return licenseUserName;
	}
	public void setLicenseUserName(String licenseUserName) {
		this.licenseUserName = licenseUserName;
	}
	public String getLicenseImgName() {
		return licenseImgName;
	}
	public void setLicenseImgName(String licenseImageName) {
		this.licenseImgName = licenseImageName;
	}
}
