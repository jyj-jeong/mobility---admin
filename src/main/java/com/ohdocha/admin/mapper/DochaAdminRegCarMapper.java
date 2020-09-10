package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailResponse;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminRegCarMapper {

    //등록차량 list 조회
    public List<DochaAdminRegCarResponse> selectRegCarInfo(DochaAdminRegCarRequest reqParam);

    //등록차량 상세 조회
    public List<DochaAdminRegCarDetailResponse> selectRegCarDetail(DochaAdminRegCarDetailRequest reqParam);

    //등록차량 상세 옵션
    public List<DochaAdminRegCarMapper> selectRegCarDetailOption(DochaAdminRegCarDetailRequest reqParam);

    //등록차량 저장
    public int insertDcCarInfo(DochaAdminRegCarDetailRequest reqParam);

    //등록차량 보험 저장
    public int insertRegCarInsurance(DochaAdminInsuranceTemplateDetailRequest reqParam);

    //등록차량 수정
    public int updateDcCarInfo(DochaAdminRegCarDetailRequest reqParam);

    //등록차량 요금계산기
    public List<DochaAdminRegCarDetailResponse> selectReserveAmt(DochaAdminRegCarDetailRequest reqParam);

    //등록차량 휴차일등록
    public int insertDcCarInfoSuspend(DochaAdminRegCarDetailRequest reqParam);

}


