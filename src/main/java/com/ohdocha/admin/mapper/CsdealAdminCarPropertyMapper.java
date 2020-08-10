package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.property.CsdealAdminCarPropertyRequest;
import com.ohdocha.admin.domain.car.property.CsdealAdminCarPropertyResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminCarPropertyMapper {

	//차량속성 조회 (국가 제조사 등급 옵션 연료)
	public List<CsdealAdminCarPropertyResponse> selectCarPropertyInfo(CsdealAdminCarPropertyRequest reqParam);
}
