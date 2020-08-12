package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RentCompanyHolidayResponse")
public class DochaAdminRentCompanyHolidayResponse extends CommonResponseDto {
	private String holIdx;			/*휴일idx*/
	private String rtIdx;			/*제휴사idx*/
	private String holidayStartDt;	/*공휴일시작일*/
	private String holidayEndDt;	/*공휴일종료일*/
	private String holidayName;		/*휴일명*/
	private String regId;			/*등록자*/
	private String modId;			/*수정자*/
	private String delYn; 			/*삭제여부*/
	private String tholIdx;			/*중복 검색시 휴일idx*/
	public String getHolIdx() {
		return holIdx;
	}
	public void setHolIdx(String holIdx) {
		this.holIdx = holIdx;
	}
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getHolidayStartDt() {
		return holidayStartDt;
	}
	public void setHolidayStartDt(String holidayStartDt) {
		this.holidayStartDt = holidayStartDt;
	}
	public String getHolidayEndDt() {
		return holidayEndDt;
	}
	public void setHolidayEndDt(String holidayEndDt) {
		this.holidayEndDt = holidayEndDt;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
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
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getTholIdx() {
		return tholIdx;
	}
	public void setTholIdx(String tholIdx) {
		this.tholIdx = tholIdx;
	}					
}
