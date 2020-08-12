package com.ohdocha.admin.domain.rentCompany;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("CdtRentCompanyAbleareaResponse")
public class DochaAdminCdtRentCompnayAbleareaResponse extends CommonResponseDto {
	private String raIdx;			/*배달지역idx*/
	private String rtIdx;			/*제휴사idx*/
	private String lat;				/*위도*/
	private String lng;				/*경도*/
	private String latlng;			/*위,경도 데이터*/
	private String raGbnCode;		/*장단기구분코드*/
	private String regId;			/*등록자*/
	private String modId;			/*수정자*/
	private String delYn; 			/*삭제여부*/
	public String getRaIdx() {
		return raIdx;
	}
	public void setRaIdx(String raIdx) {
		this.raIdx = raIdx;
	}
	public String getRtIdx() {
		return rtIdx;
	}
	public void setRtIdx(String rtIdx) {
		this.rtIdx = rtIdx;
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
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getRaGbnCode() {
		return raGbnCode;
	}
	public void setRaGbnCode(String raGbnCode) {
		this.raGbnCode = raGbnCode;
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
