package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.insuranceTemplate.CsdealAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.CsdealAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.CsdealAdminInsuranceTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminInsuranceTemplateMapper {
	
	//차량-요금제-보험템플릿 list조회
	public List<CsdealAdminInsuranceTemplateResponse> selectInsuranceTemplateInfo(CsdealAdminInsuranceTemplateRequest reqParam);
	
	//차량-요금제-보험템플릿 상세 조회
	public List<CsdealAdminInsuranceTemplateResponse> insuranceTemplateinfoDetail(CsdealAdminInsuranceTemplateRequest reqParam);
	
	//차량-요금제-보험템플릿 수정
	public int updateInsuranceTemplate(CsdealAdminInsuranceTemplateDetailRequest reqParam);
	
	//차량-요금제-보험템플릿 신규
	public int insertInsuranceTemplate(CsdealAdminInsuranceTemplateDetailRequest reqParam);
	
}
