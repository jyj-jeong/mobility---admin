package com.ohdocha.admin.domain.common;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("responseDto")
public class CommonResponseDto {
	
	private String rowNumber           ;  //페이징시 rowNumber
	private String totalRowCount       ;  //페이징시 전체 Count조회
	
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(String totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
}


