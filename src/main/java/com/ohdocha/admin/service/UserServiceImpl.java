package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.admin.DochaAdminAdminUserInfoRequest;
import com.ohdocha.admin.domain.admin.DochaAdminAdminUserInfoResponse;
import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateRequest;
import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateResponse;
import com.ohdocha.admin.domain.user.*;
import com.ohdocha.admin.domain.rentCompany.DochaAdminRentCompanyInfoRequest;
import com.ohdocha.admin.domain.rentCompany.DochaAdminRentCompanyInfoResponse;
import com.ohdocha.admin.mapper.DochaAdminAdminUserInfoMntMapper;
import com.ohdocha.admin.mapper.DochaAdminAuthTemplateMapper;
import com.ohdocha.admin.mapper.DochaAdminRentCompanyInfoMapper;
import com.ohdocha.admin.mapper.DochaAdminUserInfoMntMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl extends ServiceExtension implements UserService {

    private final DochaAdminUserInfoMntMapper userInfoMntMapper;
    private final DochaAdminRentCompanyInfoMapper rentCompanyInfoMapper;
    private final DochaAdminAdminUserInfoMntMapper adminUserInfoMntMapper;
    private final DochaAdminAuthTemplateMapper adminAuthTemplateMapper;

    @Override
    public void getIntegratedUserList(ServiceMessage message) {
        DochaAdminUserInfoRequest userInfoRequest = new DochaAdminUserInfoRequest();

        List<DochaAdminUserInfoResponse> IntegratedUserList = userInfoMntMapper.selectUserInfo(userInfoRequest);

        message.addData("IntegratedUserList", IntegratedUserList);
    }

    @Override
    public void getUserDetail(ServiceMessage message) {
        DochaAdminUserInfoRequest userInfoRequest = new DochaAdminUserInfoRequest();
        String urIdx = message.getString("urIdx", "");

        userInfoRequest.setUrIdx(urIdx);
        DochaAdminUserInfoDetailResponse userResponse = userInfoMntMapper.selectUserInfoDetail(userInfoRequest);

        message.addData("user", userResponse);
    }

    @Override
    public void getUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest userLicenseInfoRequest = new DochaAdminUserInfoUserLicenseInfoRequest();
        String urIdx = message.getString("urIdx", "");

        userLicenseInfoRequest.setUrIdx(urIdx);
        DochaAdminUserInfoUserLicenseInfoResponse userLicenseInfo = userInfoMntMapper.selectLicenseInfo(userLicenseInfoRequest);

        message.addData("userLicenseInfo", userLicenseInfo);
    }

    @Override
    public void updateUserInfo(ServiceMessage message) {
        DochaAdminUpdateUserInfoRequest updateUserInfoRequest = message.getObject("updateUserInfoRequest", DochaAdminUpdateUserInfoRequest.class);

        int res = userInfoMntMapper.updateUserInfoDetail(updateUserInfoRequest);
    }

    @Override
    public void updateUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest updateLicenseInfoRequest = message.getObject("updateLicenseInfoRequest", DochaAdminUserInfoUserLicenseInfoRequest.class);

        int res = userInfoMntMapper.updateUserLicenseInfo(updateLicenseInfoRequest);
    }

    @Override
    public void deleteUserInfo(ServiceMessage message) {

    }

    @Override
    public void getRentShopList(ServiceMessage message) {
        DochaAdminRentCompanyInfoRequest rentCompanyInfoRequest = new DochaAdminRentCompanyInfoRequest();

        List<DochaAdminRentCompanyInfoResponse> rentCompanyInfoList = rentCompanyInfoMapper.selectRentCompanyInfo(rentCompanyInfoRequest);

        message.addData("rentCompanyInfoList", rentCompanyInfoList);
    }

    @Override
    public void getAdminList(ServiceMessage message) {
        DochaAdminAdminUserInfoRequest adminUserInfoRequest = message.getObject("adminUserInfoRequest", DochaAdminAdminUserInfoRequest.class);

        List<DochaAdminAdminUserInfoResponse> adminUserInfoList = adminUserInfoMntMapper.selectAdminUserInfo(adminUserInfoRequest);

        message.addData("adminUserInfoList", adminUserInfoList);
    }

    @Override
    public void getAuthTemplates(ServiceMessage message) {
        DochaAdminAuthTemplateRequest adminAuthTemplateRequest = message.getObject("adminAuthTemplateRequest", DochaAdminAuthTemplateRequest.class);

        List<DochaAdminAuthTemplateResponse> authTemplateList = adminAuthTemplateMapper.selectAdminTemplate(adminAuthTemplateRequest);

        message.addData("authTemplateList", authTemplateList);
    }

}
