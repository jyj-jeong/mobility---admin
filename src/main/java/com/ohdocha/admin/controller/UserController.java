package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.rentCompany.*;
import com.ohdocha.admin.domain.user.DochaAdminInsertUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminUpdateUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminUserInfoUserLicenseInfoRequest;
import com.ohdocha.admin.service.UserService;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController extends ControllerExtension {

    private final UserService userService;

    // region [ 통합 회원 ]

    /* 통합 회원 리스트 화면 */
    @GetMapping(value = "/mem")
    public String IntegratedUserInfoList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getIntegratedUserList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/integrated_user_list";
    }

    /* 통합 회원 리스트 */
    @PostMapping(value = "/api/v1.0/userInfoList.json")
    @ResponseBody
    public Object userInfoList(@RequestBody DochaAdminUserInfoRequest userInfoRequest , HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("userInfoRequest",userInfoRequest);
        serviceMessage.addData("test","test");

        userService.getIntegratedUserList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return serviceMessage.get("result");
    }

    /* 회원 상세 페이지 */
    @GetMapping(value = "/mem/detail/{urIdx}")
    public String userInfoDetail(@PathVariable String urIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("urIdx", urIdx);
        serviceMessage.addData("CRUD", "modify");

        // 회원 기본정보
        userService.getUserDetail(serviceMessage);
        // 회원 면허정보
        userService.getUserLicenseInfo(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/integrated_user_detail";
    }

    /* 회원 등록 화면 */
    @GetMapping(value = "/mem/register")
    public String userInfoDetailRegisterView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("CRUD","insert");

        modelMap.addAllAttributes(serviceMessage);
        return "user/integrated_user_detail";
    }

    /* 회원 등록 */
    @PostMapping(value = "/api/v1.0/insertUserInfo.do")
    @ResponseBody
    public Object userInfoDetailRegister(@RequestBody DochaAdminInsertUserInfoRequest insertUserInfoRequest , HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("insertUserInfoRequest",insertUserInfoRequest);

        userService.insertUserDetail(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return serviceMessage;
    }

    /* 회원 상세 */
    @PostMapping(value = "/api/v1.0/userInfoListDetail.json")
    @ResponseBody
    public Object userInfoDetail(@RequestBody DochaAdminUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("userInfoRequest", userInfoRequest);

        userService.getUserDetail(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 회원 정보 수정 */
    @PostMapping(value = "/api/v1.0/updateUserInfo.do")
    @ResponseBody
    public Object userInfoUpdate(@RequestBody DochaAdminUpdateUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("updateUserInfoRequest", userInfoRequest);

        userService.updateUserInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    /* 회원 정보 삭제 */
    @PostMapping(value = "/api/v1.0/userInfoDelete.do")
    @ResponseBody
    public Object userInfoDelete(@RequestBody DochaAdminUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("userInfoRequest", userInfoRequest);

        userService.deleteUserInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    /* 회원 면허정보 등록 */
    @PostMapping(value = "/api/v1.0/insertUserLicenseInfo.do")
    @ResponseBody
    public Object insertUserLicenseInfo(@RequestBody DochaAdminUserInfoUserLicenseInfoRequest insertUserLicenseInfo, HttpServletRequest request) {

        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("insertUserLicenseInfo", insertUserLicenseInfo);

        userService.addUserLicenseInfo(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 회원 면허정보 조회 */
    @PostMapping(value = "/api/v1.0/userLicenseInfo.json")
    @ResponseBody
    public Object UserLicenseInfo(@RequestBody DochaAdminUserInfoUserLicenseInfoRequest userLicenseInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("userLicenseInfoRequest", userLicenseInfoRequest);

        userService.getUserLicenseInfo(serviceMessage);

        return serviceMessage.get("userLicenseInfo");
    }

    /* 회원 면허정보 수정 */
    @PostMapping(value = "/api/v1.0/updateUserLicenseInfo.do")
    @ResponseBody
    public Object userLicenseUpdate(@RequestBody DochaAdminUserInfoUserLicenseInfoRequest licenseInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("updateLicenseInfoRequest", licenseInfoRequest);

        userService.updateUserLicenseInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    // endregion

    // region [ 회원사 ]

    /* 회원사 리스트 */
    @GetMapping(value = "/mem/rentshop")
    public String rentShopList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getRentShopList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/rentshop_list";
    }

    /* 회원사 등록 화면 */
    @GetMapping(value = "/mem/rentshop/register")
    public String rentShopRegister(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("CRUD", "insert");

        modelMap.addAllAttributes(serviceMessage);
        return "user/rentshop_detail";
    }

    /* 회원사 등록 */
    @PostMapping(value = "/api/v1.0/insertDcRentCompany.do")
    @ResponseBody
    public Object rentShopRegister(@RequestBody DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyDetailRequest", rentCompanyDetailRequest);

        userService.addRentShop(serviceMessage);

        return serviceMessage;
    }

    /* 회원사 상세 조회 */
    @PostMapping(value = "/api/v1.0/rentCompanyDetailInfo.json")
    @ResponseBody
    public Object rentCompanyDetailInfo(@RequestBody DochaAdminRentCompanyDetailRequest rentCompanyDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyDetailRequest", rentCompanyDetailRequest);

        userService.getRentShopDetail(serviceMessage);

        return serviceMessage.get("rentCompanyDetailResponseList");
    }


    /* 회원사 상세 페이지 */
    @GetMapping(value = "/mem/rentshop/{rtIdx}")
    public String rentShopDetail(@PathVariable String rtIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rtIdx", rtIdx);
        serviceMessage.addData("CRUD", "modify");

        modelMap.addAllAttributes(serviceMessage);
        return "user/rentshop_detail";
    }

    /* 회원사 직원 조회 */
    @PostMapping(value = "/api/v1.0/rentCompanyStaffList.json")
    @ResponseBody
    public Object rentShopStaffList(@RequestBody DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyStaffRequest", rentCompanyStaffRequest);

        userService.getRentShopStaffList(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 회원사 직원 추가 */
    @PostMapping(value = "/api/v1.0/insertDcRentCompanyStaff.do")
    @ResponseBody
    public Object insertRentCompanyStaff(@RequestBody DochaAdminDcRentCompanyStaffRequest rentCompanyStaffRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyStaffRequest", rentCompanyStaffRequest);

        userService.insertRentCompanyStaff(serviceMessage);

        return serviceMessage.get("res");
    }

    /* 회원사 수수료 정보 추가 */
    @PostMapping(value = "/api/v1.0/updateDcRentCompanyCommission.do")
    @ResponseBody
    public Object updateRentCompanyCommission(@RequestBody DochaAdminDcRentCompanyComissionRequest rentCompanyCommissionRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyCommissionRequest", rentCompanyCommissionRequest);

        userService.updateRentCompanyCommission(serviceMessage);

        return serviceMessage.get("res");
    }

    /* 회원사 영업시간 추가 */
    @PostMapping(value = "/api/v1.0/updateDcRentCompanyTime.do")
    @ResponseBody
    public Object updateRentCompanyTime(@RequestBody DochaAdminDcRentCompanyTimeRequest rentCompanyTimeRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyTimeRequest", rentCompanyTimeRequest);

        userService.updateRentCompanyTime(serviceMessage);

        return serviceMessage.get("res");

    }

    /* 예약정보 특정 시간 추가 */
    @PostMapping(value = "/api/v1.0/insertDcRentCompanyReserveMin.do")
    @ResponseBody
    public Object insertDcRentCompanyReserveMin(@RequestBody DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyReserveMinRequest", rentCompanyReserveMinRequest);

        userService.insertRentCompanyReserveMinList(serviceMessage);

        return serviceMessage;
    }


    /* 예약정보 특정 시간 리스트 */
    @PostMapping(value = "/api/v1.0/rentCompanyReserveMinList.json")
    @ResponseBody
    public Object rentCompanyReserveMinList(@RequestBody DochaAdminDcRentCompanyReserveMinRequest rentCompanyReserveMinRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyReserveMinRequest", rentCompanyReserveMinRequest);

        userService.selectRentCompanyReserveMinList(serviceMessage);

        return serviceMessage;
    }

    /* 회원사 전체 휴일 등록*/
    @GetMapping(value = "/mem/rentshop/hoilday")
    public String rentShopRegisterHoildayView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "user/holiday_registration";
    }

    /* 휴무일 리스트 */
    @PostMapping(value = "/api/v1.0/selectRentCompanyHoliday.json")
    @ResponseBody
    public Object selectRentCompanyHoliday(@RequestBody DochaAdminRentCompanyHolidayRequest rentCompanyHolidayRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rentCompanyHolidayRequest", rentCompanyHolidayRequest);

        userService.selectRentCompanyHoliday(serviceMessage);

        return serviceMessage.get("result");
    }

    // endregion

    // region [ 관리자 ]

    /* 관리자 리스트 */
    @GetMapping(value = "/mem/admin")
    public String adminList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getAdminList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/admin_list";
    }

    /* 권한 템플릿 리스트 */
    @GetMapping(value = "/mem/template")
    public String authTemplates(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getAuthTemplates(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/auth_templates";
    }

    // endregion
}
