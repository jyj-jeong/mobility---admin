package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.periodplansetting.CsdealAdminPeriodPlanSettingDetailRequest;
import com.ohdocha.admin.domain.car.plan.periodplansetting.CsdealAdminPeriodPlanSettingDetailResponse;
import com.ohdocha.admin.domain.car.plan.periodplansetting.CsdealAdminPeriodPlanSettingRequest;
import com.ohdocha.admin.domain.car.plan.periodplansetting.CsdealAdminPeriodPlanSettingResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminPeriodPlanSettingMapper {

	//차량-요금제-기간요금설정 list조회
	public List<CsdealAdminPeriodPlanSettingResponse> selectPeriodPlanInfo(CsdealAdminPeriodPlanSettingRequest reqParam);

	//차량-요금제-기간요금설정 상세
	public List<CsdealAdminPeriodPlanSettingDetailResponse> selectPeriodPlanDetail(CsdealAdminPeriodPlanSettingDetailRequest reqParam);


	//차량-요금제-기간요금설정 update
	public int updatePlanSettingDetail(CsdealAdminPeriodPlanSettingDetailRequest reqParam);

	//차량-요금제-기간요금설정 insert
	public int insertPlanSettingDetail(CsdealAdminPeriodPlanSettingDetailRequest reqParam);

}
