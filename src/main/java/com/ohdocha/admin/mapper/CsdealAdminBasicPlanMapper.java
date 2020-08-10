package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.basicplan.CsdealAdminBaiscPlanDetailRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.CsdealAdminBaiscPlanRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.CsdealAdminBasicPlanDetailResponse;
import com.ohdocha.admin.domain.car.plan.basicplan.CsdealAdminBasicPlanResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminBasicPlanMapper {

	//기본요금제 list 조회
	public List<CsdealAdminBasicPlanResponse> selectBasicPlan(CsdealAdminBaiscPlanRequest reqParam);

	//기본요금제 상세 조회
	public List<CsdealAdminBasicPlanDetailResponse> selectBasicPlanDetail(CsdealAdminBaiscPlanDetailRequest reqParam);

	//기본요금 수정
	public int updateBasicPlanInfo(CsdealAdminBaiscPlanDetailRequest reqParam);

	//기본요금 신규
	public int insertBasicPlanInfo(CsdealAdminBaiscPlanDetailRequest reqParam);

}
