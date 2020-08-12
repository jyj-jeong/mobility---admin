package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.property.DochaAdminCarPropertyRequest;
import com.ohdocha.admin.domain.car.property.DochaAdminCarPropertyResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminCarPropertyMapper {

	//차량속성 조회 (국가 제조사 등급 옵션 연료)
	public List<DochaAdminCarPropertyResponse> selectCarPropertyInfo(DochaAdminCarPropertyRequest reqParam);
}
