package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.rentCompany.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminRentCompanyInfoMapper {

    // 회원사 정보
    public List<CsdealAdminRentCompanyInfoResponse> selectRentCompanyInfo(CsdealAdminRentCompanyInfoRequest reqParam);

    public List<CsdealAdminRentCompanyInfoResponse> selectRentCompanyDetailInfo(CsdealAdminRentCompanyDetailRequest reqParam);

    public int updateCdtRentCompany(CsdealAdminRentCompanyDetailRequest reqParam);

    public int insertCdtRentCompany(CsdealAdminRentCompanyDetailRequest reqParam);

    // 회원사 - 직원정보
    public List<CsdealAdminCdtRentCompnayStaffResponse> selectrentCompanyStaffList(CsdealAdminCdtRentCompnayStaffRequest reqParam);

    public int updateCdtRentStaff(CsdealAdminCdtRentCompnayStaffRequest reqParam);

    public int insertCdtRentCompanyStaff(CsdealAdminCdtRentCompnayStaffRequest reqParam);

    public int updateCdtRentStaffUserinfo(CsdealAdminCdtRentCompnayStaffRequest reqParam);

    // 회원사 - 수수료정보
    public List<CsdealAdminCdtRentCompnayComissionResponse> selectRentCompanyComission(CsdealAdminCdtRentCompnayComissionRequest reqParam);

    public int updateCdtRentCompanyComission(CsdealAdminCdtRentCompnayComissionRequest reqParam);

    public int insertCdtRentCompanyComission(CsdealAdminCdtRentCompnayComissionRequest reqParam);

    // 회원사 - 예약정보(회원사 영업, 운영시간 체크 정보)
    public List<CsdealAdminCdtRentCompnayTimeResponse> selectRentCompanyTime(CsdealAdminCdtRentCompnayTimeRequest reqParam);

    public int updateCdtRentCompanyTime(CsdealAdminCdtRentCompnayTimeRequest reqParam);

    public int insertCdtRentCompanyTime(CsdealAdminCdtRentCompnayTimeRequest reqParam);

    // 회원사 - 특정일자 예약일자 FR~TO, 예약 최소시간 정보
    public List<CsdealAdminCdtRentCompanyReseveMinResponse> selectCdtRentCompanyReseveMinList(CsdealAdminCdtRentCompanyReseveMinRequest reqParam);

    public int insertCdtRentCompanyReserveMin(CsdealAdminCdtRentCompanyReseveMinRequest reqParam);

    public int updateCdtRentCompanyReserveMin(CsdealAdminCdtRentCompanyReseveMinRequest reqParam);

    // 회원사 - 휴무일 정보
    public List<CsdealAdminRentCompanyHolidayResponse> selectRentCompanyHoliday(CsdealAdminRentCompanyHolidayRequest reqParam);

    public int insertRentCompanyHoliday(CsdealAdminRentCompanyHolidayRequest reqParam);

    public int updateRentCompanyHoliday(CsdealAdminRentCompanyHolidayRequest reqParam);

    // 회원사 - 장단기 배달지역
    public List<CsdealAdminCdtRentCompnayAbleareaResponse> selectCdtRentCompanyAblearea(CsdealAdminCdtRentCompnayAbleareaRequest reqParam);

    public int insertCdtRentCompanyAblearea(CsdealAdminCdtRentCompnayAbleareaRequest reqParam);

    public int updateCdtRentCompanyAblearea(CsdealAdminCdtRentCompnayAbleareaRequest reqParam);


}


