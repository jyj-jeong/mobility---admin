package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.authTemplate.CsdealAdminAuthTemplateRequest;
import com.ohdocha.admin.domain.authTemplate.CsdealAdminAuthTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminAuthTemplateMapper {

	/*
	 * 권한 템플릿 리스트 조회
	 * */
	public List<CsdealAdminAuthTemplateResponse> selectAdminTemplate(CsdealAdminAuthTemplateRequest reqParam);

}
