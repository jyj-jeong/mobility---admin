package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.admin.DochaAdminAdminUserInfoRequest;
import com.ohdocha.admin.domain.admin.DochaAdminAdminUserInfoResponse;
import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateRequest;
import com.ohdocha.admin.domain.authTemplate.DochaAdminAuthTemplateResponse;
import com.ohdocha.admin.domain.menu.DochaAdminMenuTemplateResponse;
import com.ohdocha.admin.domain.rentCompany.*;
import com.ohdocha.admin.domain.user.*;
import com.ohdocha.admin.mapper.DochaAdminAdminUserInfoMntMapper;
import com.ohdocha.admin.mapper.DochaAdminAuthTemplateMapper;
import com.ohdocha.admin.mapper.DochaAdminRentCompanyInfoMapper;
import com.ohdocha.admin.mapper.DochaAdminUserInfoMntMapper;
import com.ohdocha.admin.util.KeyMaker;
import com.ohdocha.admin.util.ServiceMessage;
import com.ohdocha.admin.util.TextUtils;
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
        if (message.get("test") != null){
            DochaAdminUserInfoRequest userInfoRequest = message.getObject("userInfoRequest", DochaAdminUserInfoRequest.class);

            List<DochaAdminUserInfoResponse> userInfoResponseList = userInfoMntMapper.selectUserInfo(userInfoRequest);

            message.addData("result", userInfoResponseList);
        }else {
            DochaAdminUserInfoRequest userInfoRequest = new DochaAdminUserInfoRequest();

            List<DochaAdminUserInfoResponse> IntegratedUserList = userInfoMntMapper.selectUserInfo(userInfoRequest);

            message.addData("IntegratedUserList", IntegratedUserList);
        }
    }

    @Override
    public void insertUserDetail(ServiceMessage message) {
        DochaAdminInsertUserInfoRequest insertUserInfoRequest = message.getObject("insertUserInfoRequest", DochaAdminInsertUserInfoRequest.class);

        String urIdx = TextUtils.getKeyDefault("UR");
        insertUserInfoRequest.setUrIdx(urIdx);

        int res = userInfoMntMapper.insertUserInfo(insertUserInfoRequest);

        message.addData("res", res);
        // TODO DB 저장된 urIdx 불러오도록 수정
        message.addData("urIdx", insertUserInfoRequest.getUrIdx());
    }

    @Override
    public void getUserDetail(ServiceMessage message) {
        DochaAdminUserInfoRequest userInfoRequest = new DochaAdminUserInfoRequest();
        if (message.getObject("userInfoRequest",DochaAdminUserInfoRequest.class) == null){
            String urIdx = message.getString("urIdx", "");

            userInfoRequest.setUrIdx(urIdx);
            List<DochaAdminUserInfoDetailResponse> userResponse = userInfoMntMapper.selectUserInfoDetail(userInfoRequest);

            message.addData("user", userResponse);
        }else{
            userInfoRequest =  message.getObject("userInfoRequest",DochaAdminUserInfoRequest.class);
            List<DochaAdminUserInfoDetailResponse> userResponse = userInfoMntMapper.selectUserInfoDetail(userInfoRequest);

            message.addData("result", userResponse);
        }

    }

    @Override
    public void getUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest userLicenseInfoRequest = new DochaAdminUserInfoUserLicenseInfoRequest();
        if (message.getObject("userLicenseInfoRequest",DochaAdminUserInfoUserLicenseInfoRequest.class) == null) {
            String urIdx = message.getString("urIdx", "");

            userLicenseInfoRequest.setUrIdx(urIdx);

            List<DochaAdminUserInfoUserLicenseInfoResponse> userLicenseInfo = userInfoMntMapper.selectLicenseInfo(userLicenseInfoRequest);

            message.addData("userLicenseInfo", userLicenseInfo);
        }else {
            userLicenseInfoRequest = message.getObject("userLicenseInfoRequest", DochaAdminUserInfoUserLicenseInfoRequest.class);

            List<DochaAdminUserInfoUserLicenseInfoResponse> userLicenseInfo = userInfoMntMapper.selectLicenseInfo(userLicenseInfoRequest);

            message.addData("userLicenseInfo", userLicenseInfo);
        }
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
        DochaAdminUserInfoRequest userInfoRequest = message.getObject("userInfoRequest", DochaAdminUserInfoRequest.class);

        int res = userInfoMntMapper.deleteUserInfo(userInfoRequest);


    }

    @Override
    public void addUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest insertUserLicenseInfo = message.getObject("insertUserLicenseInfo", DochaAdminUserInfoUserLicenseInfoRequest.class);

        String ulIdx = TextUtils.getKeyDefault("UL");
        insertUserLicenseInfo.setUlIdx(ulIdx);

        int res = userInfoMntMapper.insertUserLicenseInfo(insertUserLicenseInfo);
    }

    @Override
    public void addRentShop(ServiceMessage message) {
        DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest = message.getObject("rentCompanyDetailRequest", DochaAdminRentCompanyDetailRequest.class);

        String rtIdx = TextUtils.getKeyDefault("RT");
        rentCompanyDetailRequest.setRtIdx(rtIdx);

        int res = rentCompanyInfoMapper.insertDcRentCompany(rentCompanyDetailRequest);

        message.addData("rtIdx",rtIdx);
    }

    @Override
    public void getRentShopDetail(ServiceMessage message) {
        DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest = message.getObject("rentCompanyDetailRequest", DochaAdminRentCompanyDetailRequest.class);

        List<DochaAdminRentCompanyInfoResponse> rentCompanyDetailResponseList = rentCompanyInfoMapper.selectRentCompanyDetailInfo(rentCompanyDetailRequest);

        message.addData("rentCompanyDetailResponseList",rentCompanyDetailResponseList);
    }

    @Override
    public void getRentShopList(ServiceMessage message) {
        DochaAdminRentCompanyInfoRequest rentCompanyInfoRequest = new DochaAdminRentCompanyInfoRequest();

        List<DochaAdminRentCompanyInfoResponse> rentCompanyInfoList = rentCompanyInfoMapper.selectRentCompanyInfo(rentCompanyInfoRequest);

        message.addData("rentCompanyInfoList", rentCompanyInfoList);
    }

    @Override
    public void getRentShopStaffList(ServiceMessage message) {
        DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest = message.getObject("rentCompanyStaffRequest", DochaAdminDcRentCompanyStaffRequest.class);

        List<DochaAdminDcRentCompanyStaffResponse> rentCompanyStaffResponsesList = rentCompanyInfoMapper.selectrentCompanyStaffList(rentCompanyStaffRequest);

        message.addData("result", rentCompanyStaffResponsesList);
    }

    @Override
    public void updateRentCompanyCommission(ServiceMessage message) {
        DochaAdminDcRentCompanyComissionRequest rentCompanyCommissionRequest = message.getObject("rentCompanyCommissionRequest", DochaAdminDcRentCompanyComissionRequest.class);

        //회원사 수수료 정보 유무 확인
        List<DochaAdminDcRentCompanyComissionResponse> rentCompanyCommissionReques = rentCompanyInfoMapper.selectRentCompanyComission(rentCompanyCommissionRequest);
        int rentCompanyCommissionCnt = rentCompanyCommissionReques.size();

        int res = 0;
        if (rentCompanyCommissionCnt == 0){
            res = rentCompanyInfoMapper.insertDcRentCompanyComission(rentCompanyCommissionRequest);
        }else {
            res = rentCompanyInfoMapper.updateDcRentCompanyComission(rentCompanyCommissionRequest);
        }

        message.addData("res ", res);

    }

    @Override
    public void updateRentCompanyTime(ServiceMessage message) {
        DochaAdminDcRentCompanyTimeRequest rentCompanyTimeRequest = message.getObject("rentCompanyTimeRequest", DochaAdminDcRentCompanyTimeRequest.class);

        //회원사 예약정보 유무 확인
        List<DochaAdminDcRentCompanyTimeResponse> rentCompanyTimeResponseList = rentCompanyInfoMapper.selectRentCompanyTime(rentCompanyTimeRequest);
        int rentCompanyTimeCnt = rentCompanyTimeResponseList.size();

        int res = 0;
        if (rentCompanyTimeCnt == 0){
            res = rentCompanyInfoMapper.insertDcRentCompanyTime(rentCompanyTimeRequest);
        }else {
            res = rentCompanyInfoMapper.updateDcRentCompanyTime(rentCompanyTimeRequest);
        }

        message.addData("res ", res);
    }

    @Override
    public void insertRentCompanyReserveMinList(ServiceMessage message) {
        DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest = message.getObject("rentCompanyReserveMinRequest", DochaAdminDcRentCompanyReserveMinRequest.class);
        String minIdx = KeyMaker.getInsetance().getKeyDeafult("min");
        rentCompanyReserveMinRequest.setMinIdx(minIdx);

        int res = rentCompanyInfoMapper.insertDcRentCompanyReserveMin(rentCompanyReserveMinRequest);

        message.addData("result", res);
    }

    @Override
    public void insertRentCompanyStaff(ServiceMessage message) {
        DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest = message.getObject("rentCompanyStaffRequest", DochaAdminDcRentCompanyStaffRequest.class);

         int res = rentCompanyInfoMapper.insertDcRentCompanyStaff(rentCompanyStaffRequest);

        message.addData("res", res);
    }

    @Override
    public void selectRentCompanyReserveMinList(ServiceMessage message) {
        DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest = message.getObject("rentCompanyReserveMinRequest", DochaAdminDcRentCompanyReserveMinRequest.class);

        List<DochaAdminDcRentCompanyReserveMinResponse> rentCompanyReserveMinResponseList = rentCompanyInfoMapper.selectDcRentCompanyReserveMinList(rentCompanyReserveMinRequest);

        message.addData("result", rentCompanyReserveMinResponseList);
    }

    @Override
    public void insertRentCompanyHoliday(ServiceMessage message) {
        DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest = message.getObject("rentCompanyHolidayRequest", DochaAdminRentCompanyHolidayRequest.class);

        String holIdx = KeyMaker.getInsetance().getKeyDeafult("hl");
        rentCompanyHolidayRequest.setHolIdx(holIdx);

        int res = rentCompanyInfoMapper.insertRentCompanyHoliday(rentCompanyHolidayRequest);

        message.addData("result", res);
    }

    @Override
    public void selectRentCompanyHoliday(ServiceMessage message) {
        DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest = message.getObject("rentCompanyHolidayRequest", DochaAdminRentCompanyHolidayRequest.class);

        List<DochaAdminRentCompanyHolidayResponse> rentCompanyHolidayResponseList = rentCompanyInfoMapper.selectRentCompanyHoliday(rentCompanyHolidayRequest);

        message.addData("result", rentCompanyHolidayResponseList);
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

    @Override
    public void insertAuthTemplate(ServiceMessage message) {

    }

    @Override
    public void selectMenuTemplateList(ServiceMessage message) {
        List<DochaAdminMenuTemplateResponse> adminMenuTemplateResponseList = adminUserInfoMntMapper.selectMenuTemplateList();

        message.addData("result", adminMenuTemplateResponseList);
    }

}
