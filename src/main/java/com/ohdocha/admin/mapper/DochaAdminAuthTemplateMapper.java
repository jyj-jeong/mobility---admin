package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateRequest;
import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminAuthTemplateMapper {

	/*
	 * 권한 템플릿 리스트 조회
	 * */
	public List<DochaAdminAuthTemplateResponse> selectAdminTemplate(DochaAdminAuthTemplateRequest reqParam);

}
