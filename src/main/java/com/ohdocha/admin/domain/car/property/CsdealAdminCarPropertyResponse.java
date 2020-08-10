package com.ohdocha.admin.domain.car.property;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("carPropertyResponse")
public class CsdealAdminCarPropertyResponse extends CommonResponseDto {
	
	private String pCode; 		//부모코드
	private String codeValue;  	//코드명
	private String code;  		//코드
	private String propertyCnt; //속성별 갯수 Count
	private String codeIdx;
	private String rtCode;
	
	
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPropertyCnt() {
		return propertyCnt;
	}
	public void setPropertyCnt(String propertyCnt) {
		this.propertyCnt = propertyCnt;
	}
	public String getCodeIdx() {
		return codeIdx;
	}
	public void setCodeIdx(String codeIdx) {
		this.codeIdx = codeIdx;
	}
	public String getRtCode() {
		return rtCode;
	}
	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	

}
