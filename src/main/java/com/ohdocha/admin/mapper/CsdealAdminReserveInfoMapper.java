package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.reserve.reserveInfoMnt.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminReserveInfoMapper {

    public List<CsdealAdminReserveInfoResponse> selectReserveInfoList(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealAdminReserveInfoResponse> selectMatchingServiceInfoList(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealAdminReserveInfoResponse> selectPaymentInfoList(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealAdminReserveInfoDetailResponse> selectPaymentList(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealAdminReserveInfoDetailResponse> selectReserveInfo(CsdealAdminReserveInfoRequest reqParam);

    public int updateReserveInfo(CsdealAdminReserveInfoDetailRequest reqParam);

    public int insertReserveInfo(CsdealAdminReserveInfoDetailRequest reqParam);

    public List<CsdealAdminReserveInfoDetailResponse> reserveInfoCheck(CsdealAdminReserveInfoDetailRequest reqParam);

    public CsdealAdminReserveInfoResponse selectUserInfo(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealRentCompanyDto> selectCompanyList(CsdealAdminReserveInfoRequest reqParam);

    public CsdealRentCompanyDto selectCompanyInfo(CsdealAdminReserveInfoRequest reqParam);

    public List<CsdealCarDto> selectCompanyInfoAndCarInfo(CsdealAdminReserveInfoRequest reqParam);

    public CsdealCarDto selectCarInfo(CsdealAdminReserveInfoRequest reqParam);

    public CsdealCarDto selectCarInsuranceInfo(CsdealAdminReserveInfoRequest reqParam);

}
