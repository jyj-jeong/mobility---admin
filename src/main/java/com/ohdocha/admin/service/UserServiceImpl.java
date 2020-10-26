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

import java.text.SimpleDateFormat;
import java.util.Date;
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
        DochaAdminUserInfoRequest userInfoRequest;
        if (message.get("userInfoRequest") != null){
            userInfoRequest = message.getObject("userInfoRequest", DochaAdminUserInfoRequest.class);
        }else {
            userInfoRequest = new DochaAdminUserInfoRequest();
        }

        List<DochaAdminUserInfoResponse> userInfoResponseList = userInfoMntMapper.selectUserInfo(userInfoRequest);

        if (userInfoResponseList.size() != 0){
            message.addData("code", 200);
            message.addData("result", userInfoResponseList);
        }else {
            message.addData("code", 400);
            message.addData("errMsg", "유저 정보를 불러오는데 실패했습니다.");
        }
    }

    @Override
    public void insertUserDetail(ServiceMessage message) {
        DochaAdminInsertUserInfoRequest insertUserInfoRequest = message.getObject("insertUserInfoRequest", DochaAdminInsertUserInfoRequest.class);

        String urIdx = TextUtils.getKeyDefault("UR");
        insertUserInfoRequest.setUrIdx(urIdx);

        int res = userInfoMntMapper.insertUserInfo(insertUserInfoRequest);

        if (res == 1){
            message.addData("code", 200);
            // TODO DB 저장된 urIdx 불러오도록 수정
            message.addData("urIdx", insertUserInfoRequest.getUrIdx());
        }else{
            message.addData("code", 400);
            message.addData("errMsg", "회원 저장에 실패했습니다.");
        }
    }

    @Override
    public void getUserDetail(ServiceMessage message) {
        DochaAdminUserInfoRequest userInfoRequest = new DochaAdminUserInfoRequest();
        List<DochaAdminUserInfoDetailResponse> userResponse;

        if (message.getObject("userInfoRequest",DochaAdminUserInfoRequest.class) == null){

            String urIdx = message.getString("urIdx", "");
            userInfoRequest.setUrIdx(urIdx);

        }else{

            userInfoRequest =  message.getObject("userInfoRequest",DochaAdminUserInfoRequest.class);

        }

        userResponse = userInfoMntMapper.selectUserInfoDetail(userInfoRequest);

        if (userResponse.size() != 0){
            message.addData("code", 200);
            message.addData("result", userResponse);
        }else {
            message.addData("code", 400);
        }

    }

    @Override
    public void getUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest userLicenseInfoRequest = new DochaAdminUserInfoUserLicenseInfoRequest();
        List<DochaAdminUserInfoUserLicenseInfoResponse> userLicenseInfo;

        if (message.getObject("userLicenseInfoRequest",DochaAdminUserInfoUserLicenseInfoRequest.class) == null) {

            String urIdx = message.getString("urIdx", "");
            userLicenseInfoRequest.setUrIdx(urIdx);

        }else {

            userLicenseInfoRequest = message.getObject("userLicenseInfoRequest", DochaAdminUserInfoUserLicenseInfoRequest.class);

        }

        userLicenseInfo = userInfoMntMapper.selectLicenseInfo(userLicenseInfoRequest);

        if (userLicenseInfo.size() != 0){
            message.addData("code", 200);
            message.addData("result", userLicenseInfo);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void updateUserInfo(ServiceMessage message) {
        DochaAdminUpdateUserInfoRequest updateUserInfoRequest = message.getObject("updateUserInfoRequest", DochaAdminUpdateUserInfoRequest.class);

        int res = userInfoMntMapper.updateUserInfoDetail(updateUserInfoRequest);

        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void updateUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest updateLicenseInfoRequest = message.getObject("updateLicenseInfoRequest", DochaAdminUserInfoUserLicenseInfoRequest.class);

        int res = userInfoMntMapper.updateUserLicenseInfo(updateLicenseInfoRequest);

        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void deleteUserInfo(ServiceMessage message) {
        DochaAdminUserInfoRequest userInfoRequest = message.getObject("userInfoRequest", DochaAdminUserInfoRequest.class);

        int res = userInfoMntMapper.deleteUserInfo(userInfoRequest);

        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
        }

    }

    @Override
    public void addUserLicenseInfo(ServiceMessage message) {
        DochaAdminUserInfoUserLicenseInfoRequest insertUserLicenseInfo = message.getObject("insertUserLicenseInfo", DochaAdminUserInfoUserLicenseInfoRequest.class);

        String ulIdx = TextUtils.getKeyDefault("UL");
        insertUserLicenseInfo.setUlIdx(ulIdx);

        int res = userInfoMntMapper.insertUserLicenseInfo(insertUserLicenseInfo);

        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void addRentShop(ServiceMessage message) {
        DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest = message.getObject("rentCompanyDetailRequest", DochaAdminRentCompanyDetailRequest.class);

        String rtIdx = TextUtils.getKeyDefault("RT");
        rentCompanyDetailRequest.setRtIdx(rtIdx);

        int res = rentCompanyInfoMapper.insertDcRentCompany(rentCompanyDetailRequest);

        if (res == 1){
            message.addData("code", 200);
            message.addData("rtIdx",rtIdx);
        }else {
            message.addData("code", 400);
            message.addData("errMsg", "회원사 정보 저장에 실패했습니다.");
        }
    }

    @Override
    public void getRentShopDetail(ServiceMessage message) {
        DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest = message.getObject("rentCompanyDetailRequest", DochaAdminRentCompanyDetailRequest.class);

        List<DochaAdminRentCompanyInfoResponse> rentCompanyDetailResponseList = rentCompanyInfoMapper.selectRentCompanyDetailInfo(rentCompanyDetailRequest);

        if (rentCompanyDetailResponseList.size() != 0){
            message.addData("code", 200);
            message.addData("result",rentCompanyDetailResponseList);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void getRentShopList(ServiceMessage message) {
        DochaAdminRentCompanyInfoRequest rentCompanyInfoRequest = new DochaAdminRentCompanyInfoRequest();

        List<DochaAdminRentCompanyInfoResponse> rentCompanyInfoList = rentCompanyInfoMapper.selectRentCompanyInfo(rentCompanyInfoRequest);

        if (rentCompanyInfoList.size() != 0){
            message.addData("code", 200);
            message.addData("result", rentCompanyInfoList);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void getRentShopStaffList(ServiceMessage message) {
        DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest = message.getObject("rentCompanyStaffRequest", DochaAdminDcRentCompanyStaffRequest.class);

        List<DochaAdminDcRentCompanyStaffResponse> rentCompanyStaffResponsesList = rentCompanyInfoMapper.selectrentCompanyStaffList(rentCompanyStaffRequest);

        if (rentCompanyStaffResponsesList.size() != 0){
            message.addData("code", 200);
            message.addData("result", rentCompanyStaffResponsesList);
        }else {
            message.addData("code", 400);
        }
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

        if (res == 1){
            message.addData("code", 200);
        }else message.addData("code", 400);

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

        if (res == 1){
            message.addData("code", 200);
        }else message.addData("code", 400);

    }

    @Override
    public void insertRentCompanyReserveMinList(ServiceMessage message) {
        DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest = message.getObject("rentCompanyReserveMinRequest", DochaAdminDcRentCompanyReserveMinRequest.class);

        String minIdx = KeyMaker.getInsetance().getKeyDeafult("min");
        rentCompanyReserveMinRequest.setMinIdx(minIdx);

        int res = rentCompanyInfoMapper.insertDcRentCompanyReserveMin(rentCompanyReserveMinRequest);

        if (res == 1){
            message.addData("code", 200);
        }else message.addData("code", 400);

    }

    @Override
    public void insertRentCompanyStaff(ServiceMessage message) {
        DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest = message.getObject("rentCompanyStaffRequest", DochaAdminDcRentCompanyStaffRequest.class);

        int res = rentCompanyInfoMapper.insertDcRentCompanyStaff(rentCompanyStaffRequest);
        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
            message.addData("errMsg", "담당자 정보 저장에 실패했습니다.");
        }
    }

    @Override
    public void updateRentCompanyStaff(ServiceMessage message) {
        DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest = message.getObject("rentCompanyStaffRequest", DochaAdminDcRentCompanyStaffRequest.class);

        int res = rentCompanyInfoMapper.updateDcRentStaff(rentCompanyStaffRequest);
        if (res == 1){
            message.addData("code", 200);
        }else {
            message.addData("code", 400);
            message.addData("errMsg", "담당자 정보 저장에 실패했습니다.");
        }
    }

    @Override
    public void selectRentCompanyReserveMinList(ServiceMessage message) {
        DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest = message.getObject("rentCompanyReserveMinRequest", DochaAdminDcRentCompanyReserveMinRequest.class);

        List<DochaAdminDcRentCompanyReserveMinResponse> rentCompanyReserveMinResponseList = rentCompanyInfoMapper.selectDcRentCompanyReserveMinList(rentCompanyReserveMinRequest);

        if (rentCompanyReserveMinResponseList.size() != 0){
            message.addData("code", 200);
            message.addData("result", rentCompanyReserveMinResponseList);
        }
    }

    @Override
    public void insertRentCompanyHoliday(ServiceMessage message) {
        DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest = message.getObject("rentCompanyHolidayRequest", DochaAdminRentCompanyHolidayRequest.class);

        String holIdx = KeyMaker.getInsetance().getKeyDeafult("hl");
        rentCompanyHolidayRequest.setHolIdx(holIdx);

        int res = rentCompanyInfoMapper.insertRentCompanyHoliday(rentCompanyHolidayRequest);

        if (res == 1){
            message.addData("code", 200);
        }else message.addData("code", 400);

    }

    @Override
    public void deleteRentCompanyHoliday(ServiceMessage message) {
        DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest = message.getObject("rentCompanyHolidayRequest", DochaAdminRentCompanyHolidayRequest.class);

        int res = rentCompanyInfoMapper.deleteRentCompanyHoliday(rentCompanyHolidayRequest);

        if (res == 1){
            message.addData("code", 200);
        }else message.addData("code", 400);

    }

    @Override
    public void selectRentCompanyHoliday(ServiceMessage message) {
        DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest = message.getObject("rentCompanyHolidayRequest", DochaAdminRentCompanyHolidayRequest.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        rentCompanyHolidayRequest.setHolidayStartDt(today);

        List<DochaAdminRentCompanyHolidayResponse> rentCompanyHolidayResponseList = rentCompanyInfoMapper.selectRentCompanyHoliday(rentCompanyHolidayRequest);

        if (rentCompanyHolidayResponseList.size() != 0){
            message.addData("code", 200);
            message.addData("result", rentCompanyHolidayResponseList);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void getAdminList(ServiceMessage message) {
        DochaAdminAdminUserInfoRequest adminUserInfoRequest = message.getObject("adminUserInfoRequest", DochaAdminAdminUserInfoRequest.class);

        List<DochaAdminAdminUserInfoResponse> adminUserInfoList = adminUserInfoMntMapper.selectAdminUserInfo(adminUserInfoRequest);

        if (adminUserInfoList.size() != 0){
            message.addData("code", 200);
            message.addData("result", adminUserInfoList);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void getAuthTemplates(ServiceMessage message) {
        DochaAdminAuthTemplateRequest adminAuthTemplateRequest = message.getObject("adminAuthTemplateRequest", DochaAdminAuthTemplateRequest.class);

        List<DochaAdminAuthTemplateResponse> authTemplateList = adminAuthTemplateMapper.selectAdminTemplate(adminAuthTemplateRequest);

        if (authTemplateList.size() != 0){
            message.addData("code", 200);
            message.addData("result", authTemplateList);
        }else {
            message.addData("code", 400);
        }
    }

    @Override
    public void insertAuthTemplate(ServiceMessage message) {

    }

    @Override
    public void selectMenuTemplateList(ServiceMessage message) {
        List<DochaAdminMenuTemplateResponse> adminMenuTemplateResponseList = adminUserInfoMntMapper.selectMenuTemplateList();

        if (adminMenuTemplateResponseList.size() != 0){
            message.addData("code", 200);
            message.addData("result", adminMenuTemplateResponseList);
        }else {
            message.addData("code", 400);
        }
    }

}
