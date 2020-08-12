package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanDetailRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBasicPlanDetailResponse;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBasicPlanResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminBasicPlanMapper {

	//기본요금제 list 조회
	public List<DochaAdminBasicPlanResponse> selectBasicPlan(DochaAdminBaiscPlanRequest reqParam);

	//기본요금제 상세 조회
	public List<DochaAdminBasicPlanDetailResponse> selectBasicPlanDetail(DochaAdminBaiscPlanDetailRequest reqParam);

	//기본요금 수정
	public int updateBasicPlanInfo(DochaAdminBaiscPlanDetailRequest reqParam);

	//기본요금 신규
	public int insertBasicPlanInfo(DochaAdminBaiscPlanDetailRequest reqParam);

}
