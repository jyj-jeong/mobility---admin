package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminInsuranceTemplateMapper {
	
	//차량-요금제-보험템플릿 list조회
	public List<DochaAdminInsuranceTemplateResponse> selectInsuranceTemplateInfo(DochaAdminInsuranceTemplateRequest reqParam);
	
	//차량-요금제-보험템플릿 상세 조회
	public List<DochaAdminInsuranceTemplateResponse> insuranceTemplateinfoDetail(DochaAdminInsuranceTemplateRequest reqParam);
	
	//차량-요금제-보험템플릿 수정
	public int updateInsuranceTemplate(DochaAdminInsuranceTemplateDetailRequest reqParam);
	
	//차량-요금제-보험템플릿 신규
	public int insertInsuranceTemplate(DochaAdminInsuranceTemplateDetailRequest reqParam);
	
}
