package com.ohdocha.admin.domain.rentCompany;


import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("rentCompanyDetailResponse")
public class DochaAdminRentCompanyDetailResponse extends CommonResponseDto {
	
	//DC_RETNCOMPANYINFO
	private String rtIdx                     ; //제휴사idx
	private String rtPIdx                    ; //부모제휴사idx
	private String companyName               ; //제휴사명
	private String companyZipcode            ; //우편번호
	private String companyAddress            ; //주소
	private String companyAddressDetail      ; //상세주소
	private String lat                       ; //위도
	private String lng                       ; //경도
	private String establishedDate           ; //설립일
	private String companyRegistrationNumber ; //사업자등록번호
	private String companyRegistrationImg    ; //사업자등록증이미지idx
	private String accountBank               ; //정산계좌은행
	private String accountNumber             ; //정산계좌번호
	private String accountHolder             ; //정산계좌예금주
	private String accountImgIdx             ; //정산통장이미지idx
	private String longtermRentYn            ; //장기렌트이용여부
	private String shorttermRentYn           ; //단기렌트이용여부
	private String alliance_status           ; //제휴상태
	private String branchAbleYn              ; //지점보유가능여부
	private String  carCount                  ; //보유차량대수
	private String etc                       ; //비고
	private String regId                     ; //등록자
	private String regDt                     ; //등록일시
	private String modId                     ; //수정자
	private String modDt                     ; //수정일시
	private String delYn                     ; //삭제여부
	private String companyContact1           ; //회사대표번호
	private String companyContact2           ; //회사번호
	private String alarmYn                   ; //알림톡수신여부
	private String companyRegistrationName   ; //법인명
	private String branchName                ; //지점명
	private String accessYn                  ; //접속허용여부
	
	//DC_RENTCOMPANY_STAFF 담당자정보
//	private String rsIdx                     ; //제휴사직원idsx
//	private String staffName                 ; //직원명
//	private String staffContact1             ; //연락처1
//	private String staffContact2             ; //연락처2
//	private String staffEmail                ; //이메일
//	private String staffTitle                ; //직위
//	private String ownerYn                   ; //대표여부
//	private String staffTypeCode             ; //직원분류code
	
	//DC_RENT_COMPANY_COMMISSION 수수료율
	private String commissionPer			 ; //수수료율
	private String taxInvoiceCode			 ; //세금계산서발행주체
	
	//DC_RENT_COMPANY_TIME	예약정보
	private String weekdayOpenStart				             ; //평일영업시작시간
	private String weekdayOpenEnd							 ; //평일영업종료시간
	private String weekendOpenStart                          ; //주말영업시작시간
	private String weekendOpenEnd                            ; //주말영업종료시간
	private String weekdayDeliveryStart                      ; //평일배달시작시간
	private String weekdayDeliveryEnd                        ; //평일배달종료시간
	private String weekendDeliveryStart			    		 ; //주말배달시작시간
	private String weekendDeliveryEnd						 ; //주말배달종료시간
	private String returnInspectionTime                      ; //반납정비시간
	private String visitAbleTime                             ; //방문예약가능시간
	private String deliveryAbleTime                          ; //배달예약가능시간
	private String weekendReserveMinimumTime                 ; //주말공휴일최소예약시간(분)
	private String weekdayAbleDeliveryTime                   ; //평일왕복배달가능시간
	private String weekendAbleDeliveryTime                   ; //주말왕복배달가능시간
	private String weekendReserveMinimumRate                 ; //주말/공휴일 할증율
	
	//DC_RENT_COMPANY_RESERVE_MIN 특정기간 최소 예약시간 설정
//	private String minIdx                                     ; // 특정기간idx
//	private String minimumStartDt                             ; // 최소예약시간시작일
//	private String minimumEndDt                               ; // 최소에약시간종료일
//	private String minimumTime                                  ; // 최소시간(분)
	
	
	private String raGbnLt                 					 ; // 배달가능지역 장기 카운트
	private String raGbnSt                 					 ; // 배달가능지역 단기 카운트
	private int regCarCount;
	
	
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
	public String getCompanyZipcode() {
		return companyZipcode;
	}
	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
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
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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
	public String getAlliance_status() {
		return alliance_status;
	}
	public void setAlliance_status(String alliance_status) {
		this.alliance_status = alliance_status;
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
//	public String getRsIdx() {
//		return rsIdx;
//	}
//	public void setRsIdx(String rsIdx) {
//		this.rsIdx = rsIdx;
//	}
//	public String getStaffName() {
//		return staffName;
//	}
//	public void setStaffName(String staffName) {
//		this.staffName = staffName;
//	}
//	public String getStaffContact1() {
//		return staffContact1;
//	}
//	public void setStaffContact1(String staffContact1) {
//		this.staffContact1 = staffContact1;
//	}
//	public String getStaffContact2() {
//		return staffContact2;
//	}
//	public void setStaffContact2(String staffContact2) {
//		this.staffContact2 = staffContact2;
//	}
//	public String getStaffEmail() {
//		return staffEmail;
//	}
//	public void setStaffEmail(String staffEmail) {
//		this.staffEmail = staffEmail;
//	}
//	public String getStaffTitle() {
//		return staffTitle;
//	}
//	public void setStaffTitle(String staffTitle) {
//		this.staffTitle = staffTitle;
//	}
//	public String getOwnerYn() {
//		return ownerYn;
//	}
//	public void setOwnerYn(String ownerYn) {
//		this.ownerYn = ownerYn;
//	}
//	public String getStaffTypeCode() {
//		return staffTypeCode;
//	}
//	public void setStaffTypeCode(String staffTypeCode) {
//		this.staffTypeCode = staffTypeCode;
//	}
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
	public String getWeekdayOpenStart() {
		return weekdayOpenStart;
	}
	public void setWeekdayOpenStart(String weekdayOpenStart) {
		this.weekdayOpenStart = weekdayOpenStart;
	}

	public String getWeekdayOpenEnd() {
		return weekdayOpenEnd;
	}
	public void setWeekdayOpenEnd(String weekdayOpenEnd) {
		this.weekdayOpenEnd = weekdayOpenEnd;
	}
	public String getWeekendOpenStart() {
		return weekendOpenStart;
	}
	public void setWeekendOpenStart(String weekendOpenStart) {
		this.weekendOpenStart = weekendOpenStart;
	}
	public String getWeekendOpenEnd() {
		return weekendOpenEnd;
	}
	public void setWeekendOpenEnd(String weekendOpenEnd) {
		this.weekendOpenEnd = weekendOpenEnd;
	}
	public String getWeekdayDeliveryStart() {
		return weekdayDeliveryStart;
	}
	public void setWeekdayDeliveryStart(String weekdayDeliveryStart) {
		this.weekdayDeliveryStart = weekdayDeliveryStart;
	}
	public String getWeekdayDeliveryEnd() {
		return weekdayDeliveryEnd;
	}
	public void setWeekdayDeliveryEnd(String weekdayDeliveryEnd) {
		this.weekdayDeliveryEnd = weekdayDeliveryEnd;
	}
	public String getWeekendDeliveryStart() {
		return weekendDeliveryStart;
	}
	public void setWeekendDeliveryStart(String weekendDeliveryStart) {
		this.weekendDeliveryStart = weekendDeliveryStart;
	}

	
	
	public String getWeekendReserveMinimumRate() {
		return weekendReserveMinimumRate;
	}
	public void setWeekendReserveMinimumRate(String weekendReserveMinimumRate) {
		this.weekendReserveMinimumRate = weekendReserveMinimumRate;
	}
	public String getWeekendDeliveryEnd() {
		return weekendDeliveryEnd;
	}
	public void setWeekendDeliveryEnd(String weekendDeliveryEnd) {
		this.weekendDeliveryEnd = weekendDeliveryEnd;
	}
	public String getReturnInspectionTime() {
		return returnInspectionTime;
	}
	public void setReturnInspectionTime(String returnInspectionTime) {
		this.returnInspectionTime = returnInspectionTime;
	}
	public String getVisitAbleTime() {
		return visitAbleTime;
	}
	public void setVisitAbleTime(String visitAbleTime) {
		this.visitAbleTime = visitAbleTime;
	}
	public String getDeliveryAbleTime() {
		return deliveryAbleTime;
	}
	public void setDeliveryAbleTime(String deliveryAbleTime) {
		this.deliveryAbleTime = deliveryAbleTime;
	}
	public String getWeekendReserveMinimumTime() {
		return weekendReserveMinimumTime;
	}
	public void setWeekendReserveMinimumTime(String weekendReserveMinimumTime) {
		this.weekendReserveMinimumTime = weekendReserveMinimumTime;
	}
	public String getWeekdayAbleDeliveryTime() {
		return weekdayAbleDeliveryTime;
	}
	public void setWeekdayAbleDeliveryTime(String weekdayAbleDeliveryTime) {
		this.weekdayAbleDeliveryTime = weekdayAbleDeliveryTime;
	}
	public String getWeekendAbleDeliveryTime() {
		return weekendAbleDeliveryTime;
	}
	public void setWeekendAbleDeliveryTime(String weekendAbleDeliveryTime) {
		this.weekendAbleDeliveryTime = weekendAbleDeliveryTime;
	}
	public String getRaGbnLt() {
		return raGbnLt;
	}
	public void setRaGbnLt(String raGbnLt) {
		this.raGbnLt = raGbnLt;
	}
	public String getRaGbnSt() {
		return raGbnSt;
	}
	public void setRaGbnSt(String raGbnSt) {
		this.raGbnSt = raGbnSt;
	}
	//	public String getMinIdx() {
//		return minIdx;
//	}
//	public void setMinIdx(String minIdx) {
//		this.minIdx = minIdx;
//	}
//	public String getMinimumStartDt() {
//		return minimumStartDt;
//	}
//	public void setMinimumStartDt(String minimumStartDt) {
//		this.minimumStartDt = minimumStartDt;
//	}
//	public String getMinimumEndDt() {
//		return minimumEndDt;
//	}
//	public void setMinimumEndDt(String minimumEndDt) {
//		this.minimumEndDt = minimumEndDt;
//	}
//	public String getMinimumTime() {
//		return minimumTime;
//	}
//	public void setMinimumTime(String minimumTime) {
//		this.minimumTime = minimumTime;
//	}
	public int getRegCarCount() {
		return regCarCount;
	}
	public void setRegCarCount(int regCarCount) {
		this.regCarCount = regCarCount;
	}
	
	
	
	
}                                                