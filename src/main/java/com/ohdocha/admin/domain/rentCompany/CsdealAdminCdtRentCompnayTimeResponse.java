package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cdtRentCompanyTimeResponse")
public class CsdealAdminCdtRentCompnayTimeResponse extends CommonResponseDto {
	private String rtIdx; 						//제휴사idx
	private String weekdayOpenStart;			// 평일 영업시간 시작
	private String weekdayOpenEnd;				// 평일 영업시간 종료
	private String weekendOpenStart;			// 주말/공휴일 영업시간 시작
	private String weekendOpenEnd;				// 주말/공휴일 영업시간 종료
	private String weekdayDeliveryStart;		// 평일 왕복배달가능 시간 시작
	private String weekdayDeliveryEnd;			// 평일 왕복배달가능 시간 종료
	private String weekendDeliveryStart;		// 주말/공휴일 왕복배달가능시간 시작
	private String weekendDeliveryEnd;			// 주말/공휴일 왕복배달가능시간 종료
	private String weekdayAbleDeliveryTime;	// 평일 왕복배달가능 시작 시간대 설정
	private String weekendAbleDeliveryTime;	// 주말 왕복배달가능 시간 시간대 설정
	private String returnInspectionTime;		// 반납정비 시간 시간대 설정
	private String weekendReserveMinimumTime;	// 주말/공휴일 최소 예약 시간
	private String weekendReserveMinimumRate;	// 주말/공휴일 할증율
	private String regId; 						// 등록일
	private String modId; 						// 수정자
	
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
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
	public String getWeekendDeliveryEnd() {
		return weekendDeliveryEnd;
	}
	public void setWeekendDeliveryEnd(String weekendDeliveryEnd) {
		this.weekendDeliveryEnd = weekendDeliveryEnd;
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
	public String getReturnInspectionTime() {
		return returnInspectionTime;
	}
	public void setReturnInspectionTime(String returnInspectionTime) {
		this.returnInspectionTime = returnInspectionTime;
	}
	public String getWeekendReserveMinimumTime() {
		return weekendReserveMinimumTime;
	}
	public void setWeekendReserveMinimumTime(String weekendReserveMinimumTime) {
		this.weekendReserveMinimumTime = weekendReserveMinimumTime;
	}
	public String getWeekendReserveMinimumRate() {
		return weekendReserveMinimumRate;
	}
	public void setWeekendReserveMinimumRate(String weekendReserveMinimumRate) {
		this.weekendReserveMinimumRate = weekendReserveMinimumRate;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}

}
