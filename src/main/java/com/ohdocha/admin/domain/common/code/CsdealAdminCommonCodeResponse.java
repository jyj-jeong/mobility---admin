package com.ohdocha.admin.domain.common.code;

import com.ohdocha.admin.domain.common.CommonResponseDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("commonCodeResponse")
public class CsdealAdminCommonCodeResponse extends CommonResponseDto {
	
	private String codeIdx    ;  //코드idx
	private String rtCode     ;  //최상위 코드
	private String pcode      ;  //부모코드
	private String code       ;  //코드
	private String codeValue  ;  //코드명
	private String descript   ;  //설명
	private String sort       ;  //정렬
	private String rootYn     ;  //최상위 여부
	private String regId              ; //등록자
	private String modId              ; //수정자
	
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
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRootYn() {
		return rootYn;
	}
	public void setRootYn(String rootYn) {
		this.rootYn = rootYn;
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
