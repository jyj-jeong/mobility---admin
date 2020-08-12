package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.rentCompany.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminRentCompanyInfoMapper {

    // 회원사 정보
    public List<DochaAdminRentCompanyInfoResponse> selectRentCompanyInfo(DochaAdminRentCompanyInfoRequest reqParam);

    public List<DochaAdminRentCompanyInfoResponse> selectRentCompanyDetailInfo(DochaAdminRentCompanyDetailRequest reqParam);

    public int updateCdtRentCompany(DochaAdminRentCompanyDetailRequest reqParam);

    public int insertCdtRentCompany(DochaAdminRentCompanyDetailRequest reqParam);

    // 회원사 - 직원정보
    public List<DochaAdminCdtRentCompnayStaffResponse> selectrentCompanyStaffList(DochaAdminCdtRentCompnayStaffRequest reqParam);

    public int updateCdtRentStaff(DochaAdminCdtRentCompnayStaffRequest reqParam);

    public int insertCdtRentCompanyStaff(DochaAdminCdtRentCompnayStaffRequest reqParam);

    public int updateCdtRentStaffUserinfo(DochaAdminCdtRentCompnayStaffRequest reqParam);

    // 회원사 - 수수료정보
    public List<DochaAdminCdtRentCompnayComissionResponse> selectRentCompanyComission(DochaAdminCdtRentCompnayComissionRequest reqParam);

    public int updateCdtRentCompanyComission(DochaAdminCdtRentCompnayComissionRequest reqParam);

    public int insertCdtRentCompanyComission(DochaAdminCdtRentCompnayComissionRequest reqParam);

    // 회원사 - 예약정보(회원사 영업, 운영시간 체크 정보)
    public List<DochaAdminCdtRentCompnayTimeResponse> selectRentCompanyTime(DochaAdminCdtRentCompnayTimeRequest reqParam);

    public int updateCdtRentCompanyTime(DochaAdminCdtRentCompnayTimeRequest reqParam);

    public int insertCdtRentCompanyTime(DochaAdminCdtRentCompnayTimeRequest reqParam);

    // 회원사 - 특정일자 예약일자 FR~TO, 예약 최소시간 정보
    public List<DochaAdminCdtRentCompanyReseveMinResponse> selectCdtRentCompanyReseveMinList(DochaAdminCdtRentCompanyReseveMinRequest reqParam);

    public int insertCdtRentCompanyReserveMin(DochaAdminCdtRentCompanyReseveMinRequest reqParam);

    public int updateCdtRentCompanyReserveMin(DochaAdminCdtRentCompanyReseveMinRequest reqParam);

    // 회원사 - 휴무일 정보
    public List<DochaAdminRentCompanyHolidayResponse> selectRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    public int insertRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    public int updateRentCompanyHoliday(DochaAdminRentCompanyHolidayRequest reqParam);

    // 회원사 - 장단기 배달지역
    public List<DochaAdminCdtRentCompnayAbleareaResponse> selectCdtRentCompanyAblearea(DochaAdminCdtRentCompnayAbleareaRequest reqParam);

    public int insertCdtRentCompanyAblearea(DochaAdminCdtRentCompnayAbleareaRequest reqParam);

    public int updateCdtRentCompanyAblearea(DochaAdminCdtRentCompnayAbleareaRequest reqParam);


}


