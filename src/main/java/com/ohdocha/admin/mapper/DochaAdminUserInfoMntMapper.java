package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.user.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DochaAdminUserInfoMntMapper {


    /*
     * 통합회원 List
     * */
    public List<DochaAdminUserInfoResponse> selectUserInfo(DochaAdminUserInfoRequest paramDto);

    /*
     * 통합회원 상세
     * */
    public List<DochaAdminUserInfoDetailResponse> selectUserInfoDetail(DochaAdminUserInfoRequest reqParam);

    /*
     * 통합회원 정보 update
     * */
    public int updateUserInfoDetail(DochaAdminUpdateUserInfoRequest reqParam);

    /*
     * 사용자 면허정보 Update
     * */
    public int updateUserLicenseInfo(DochaAdminUserInfoUserLicenseInfoRequest reqParam);

    /*
     * 사용자 면허정보 Update
     * */
    public int insertUserLicenseInfo(DochaAdminUserInfoUserLicenseInfoRequest reqParam);

    public DochaAdminUserInfoUserLicenseInfoResponse selectLicenseImg(String ulIdx);

    public int updateLicenseImg(DochaAdminUserInfoUserLicenseInfoRequest carModelDetailRequest);


    /*
     * 사용자 면허정보 Update
     * */
    public List<DochaAdminUserInfoUserLicenseInfoResponse> selectLicenseInfo(DochaAdminUserInfoUserLicenseInfoRequest reqParam);

    /*
     * 신규 사용자 INSERT
     * */
    public int insertUserInfo(DochaAdminInsertUserInfoRequest reqParam);

    /*
     * 회원 DELETE
     * */
    public int deleteUserInfo(DochaAdminUserInfoRequest reqParam);

}
