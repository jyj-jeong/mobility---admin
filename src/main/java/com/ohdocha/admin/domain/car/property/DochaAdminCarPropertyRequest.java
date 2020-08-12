package com.ohdocha.admin.domain.car.property;

import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("carPropertyRequest")
public class DochaAdminCarPropertyRequest extends CommonRequestDto {
	
	
	private String rtCode;
	private String pCode; 		//중분류(부모코드)
	private String code;  		//소분류(자식코드)
	
	
	
	/*
	 * countType = country			국가
		countType = manufacturer	제조사
		countType = ranking			등급
		countType = fuel			연료
		countType = option			옵션
	 * 
	 * */
	private String countType;   //속성 Count
	private String codeIdx;
	
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getRtCode() {
		return rtCode;
	}
	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}
	public String getCodeIdx() {
		return codeIdx;
	}
	public void setCodeIdx(String codeIdx) {
		this.codeIdx = codeIdx;
	}
	
	
	
	
}
