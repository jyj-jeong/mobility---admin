package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.rentCompany.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminRentCompanyInfoMapper {

    // 회원사 정보
    public List<DochaAdminRentCompanyInfoResponse> selectRentCompanyInfo(DochaAdminRentCompanyInfoRequest reqParam);

    public List<DochaAdminRentCompanyInfoResponse> selectRentCompanyDetailInfo(DochaAdminRentCompanyDetailRequest reqParam);

    public int updateDcRentCompany(DochaAdminRentCompanyDetailRequest reqParam);

    public int insertDcRentCompany(DochaAdminRentCompanyDetailRequest reqParam);

    // 회원사 - 직원정보
    public List<DochaAdminDcRentCompnayStaffResponse> selectrentCompanyStaffList(DochaAdminDcRentCompnayStaffRequest reqParam);

    public int updateDcRentStaff(DochaAdminDcRentCompnayStaffRequest reqParam);

    public int insertDcRentCompanyStaff(DochaAdminDcRentCompnayStaffRequest reqParam);

    public int updateDcRentStaffUserinfo(DochaAdminDcRentCompnayStaffRequest reqParam);

    // 회원사 - 수수료정보
    public List<DochaAdminDcRentCompnayComissionResponse> selectRentCompanyComission(DochaAdminDcRentCompnayComissionRequest reqParam);

    public int updateDcRentCompanyComission(DochaAdminDcRentCompnayComissionRequest reqParam);

    public int insertDcRentCompanyComission(DochaAdminDcRentCompnayComissionRequest reqParam);

    // 회원사 - 예약정보(회원사 영업, 운영시간 체크 정보)
    public List<DochaAdminDcRentCompnayTimeResponse> selectRentCompanyTime(DochaAdminDcRentCompnayTimeRequest reqParam);

    public int updateDcRentCompanyTime(DochaAdminDcRentCompnayTimeRequest reqParam);

    public int insertDcRentCompanyTime(DochaAdminDcRentCompnayTimeRequest reqParam);

    // 회원사 - 특정일자 예약일자 FR~TO, 예약 최소시간 정보
    public List<DochaAdminDcRentCompanyReseveMinResponse> selectDcRentCompanyReseveMinList(DochaAdminDcRentCompanyReseveMinRequest reqParam);

    public int insertDcRentCompanyReserveMin(DochaAdminDcRentCompanyReseveMinRequest reqParam);

    public int updateDcRentCompanyReserveMin(DochaAdminDcRentCompanyReseveMinRequest reqParam);

    // 회원사 - 휴무일 정보
    public List<DochaAdminRentCompanyHolidayResponse> selectRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    public int insertRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    public int updateRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    // 회원사 - 장단기 배달지역
    public List<DochaAdminDcRentCompnayAbleareaResponse> selectDcRentCompanyAblearea(DochaAdminDcRentCompnayAbleareaRequest reqParam);

    public int insertDcRentCompanyAblearea(DochaAdminDcRentCompnayAbleareaRequest reqParam);

    public int updateDcRentCompanyAblearea(DochaAdminDcRentCompnayAbleareaRequest reqParam);


}


