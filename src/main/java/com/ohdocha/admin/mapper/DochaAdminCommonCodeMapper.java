package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.common.code.DochaAdminCommonCodeRequest;
import com.ohdocha.admin.domain.common.code.DochaAdminCommonCodeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminCommonCodeMapper {

	/*
	 * 공통코드 list 조회
	 * */
	public List<DochaAdminCommonCodeResponse> selectCommonCodeInfo(DochaAdminCommonCodeRequest reqParam);


	/*
	 * 공통코드 Update
	 * */
	public int updateCommonCodeInfo(DochaAdminCommonCodeRequest reqParam);

	/*
	 * 공통코드 Update
	 * */
	public int insertCommonCodeInfo(DochaAdminCommonCodeRequest reqParam);
}
