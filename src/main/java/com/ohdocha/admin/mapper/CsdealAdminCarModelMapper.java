package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelDetailResponse;
import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelRequest;
import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminCarModelMapper {

    //차량모델 list 조회
    public List<CsdealAdminCarModelResponse> selectCarModelInfo(CsdealAdminCarModelRequest reqParam);

    //차량-차량모델 상세
    public List<CsdealAdminCarModelDetailResponse> selectCarModelDetail(CsdealAdminCarModelDetailRequest reqParam);


    public List<CsdealAdminCarModelDetailResponse> selectCarModelForSelectBox(CsdealAdminCarModelDetailRequest reqParam);

    public List<CsdealAdminCarModelDetailResponse> selectCarModelDetailForSelectBox(CsdealAdminCarModelDetailRequest reqParam);

    //차량-차량모델  수정
    public int updateCarModelInfo(CsdealAdminCarModelDetailRequest reqParam);

    //차량-차량모델  신규
    public int insertCarModelInfo(CsdealAdminCarModelDetailRequest reqParam);

}
