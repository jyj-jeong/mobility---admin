package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("dcRentCompanyReserveMinResponse")
public class DochaAdminDcRentCompanyReserveMinResponse extends CommonResponseDto {
	private String minIdx;			/*특정기간idx*/
	private String rtIdx;			/*제휴사idx*/
	private String minimumStartDt;	/*최소예약시간시작일*/
	private String minimumEndDt;	/*최소예약시간종료일*/
	private String minimumTime;		/*최소시간*/
	private String regId;			/*등록자*/
	private String modId;			/*수정자*/
	private String delYn; 			/*삭제여부*/

	public String getMinIdx() {
		return minIdx;
	}
	public void setMinIdx(String minIdx) {
		this.minIdx = minIdx;
	}
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
	}
	public String getMinimumStartDt() {
		return minimumStartDt;
	}
	public void setMinimumStartDt(String minimumStartDt) {
		this.minimumStartDt = minimumStartDt;
	}
	public String getMinimumEndDt() {
		return minimumEndDt;
	}
	public void setMinimumEndDt(String minimumEndDt) {
		this.minimumEndDt = minimumEndDt;
	}
	public String getMinimumTime() {
		return minimumTime;
	}
	public void setMinimumTime(String minimumTime) {
		this.minimumTime = minimumTime;
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

}
