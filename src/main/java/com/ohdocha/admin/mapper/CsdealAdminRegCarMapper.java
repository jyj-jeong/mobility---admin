package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarDetailResponse;
import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminRegCarMapper {

    //등록차량 list 조회
    public List<CsdealAdminRegCarResponse> selectRegCarInfo(CsdealAdminRegCarRequest reqParam);


    //등록차량 상세 조회
    public List<CsdealAdminRegCarDetailResponse> selectRegCarDetail(CsdealAdminRegCarDetailRequest reqParam);


    //등록차량 상세 옵션
    public List<CsdealAdminRegCarMapper> selectRegCarDetailOption(CsdealAdminRegCarDetailRequest reqParam);

    //등록차량 저장
    public int insertCdtCarInfo(CsdealAdminRegCarDetailRequest reqParam);

    //등록차량 수정
    public int updateCdtCarInfo(CsdealAdminRegCarDetailRequest reqParam);

    //등록차량 요금계산기
    public List<CsdealAdminRegCarDetailResponse> selectReserveAmt(CsdealAdminRegCarDetailRequest reqParam);

    //등록차량 휴차일등록
    public int insertCdtCarInfoSuspend(CsdealAdminRegCarDetailRequest reqParam);

}


