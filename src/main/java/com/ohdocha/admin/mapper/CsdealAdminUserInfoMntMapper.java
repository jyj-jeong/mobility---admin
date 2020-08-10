package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.member.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CsdealAdminUserInfoMntMapper {


    /*
     * 통합회원 List
     * */
    public List<CsdealAdminUserInfoResponse> selectUserInfo(CsdealAdminUserInfoRequest paramDto);

    /*
     * 통합회원 상세
     * */
    public CsdealAdminUserInfoDetailResponse selectUserInfoDetail(CsdealAdminUserInfoRequest reqParam);

    /*
     * 통합회원 정보 update
     * */
    public int updateUserInfoDetail(CsdealAdminUpdateUserInfoRequest reqParam);

    /*
     * 사용자 면허정보 Update
     * */
    public int updateUserLicenseInfo(CsdealAdminUserInfoUserLicenseInfoRequest reqParam);

    /*
     * 사용자 면허정보 Update
     * */
    public int insertUserLicenseInfo(CsdealAdminUserInfoUserLicenseInfoRequest reqParam);


    /*
     * 사용자 면허정보 Update
     * */
    public List<CsdealAdminUserInfoUserLicenseInfoResponse> selectLicenseInfo(CsdealAdminUserInfoUserLicenseInfoRequest reqParam);

    /*
     * 신규 사용자 INSERT
     * */
    public int insertUserInfo(CsdealAdminInsertUserInfoRequest reqParam);

}
