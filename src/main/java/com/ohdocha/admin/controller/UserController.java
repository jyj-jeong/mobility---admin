package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.user.DochaAdminUpdateUserInfoRequest;
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
@RequestMapping(value = "/mem")
public class UserController extends ControllerExtension {

    private final UserService userService;

    /* 통합 회원 리스트 */
    @GetMapping(value = "")
    public String IntegratedUserInfoList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getIntegratedUserList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/integrated_user_list";
    }

    /* 회원 상세 페이지 */
    @GetMapping(value = "/{urIdx}")
    public String userInfoDetail(@PathVariable String urIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("urIdx", urIdx);

        // 회원 기본정보
        userService.getUserDetail(serviceMessage);
        // 회원 면허정보
        userService.getUserLicenseInfo(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/integrated_user_detail";
    }

    /* 회원 정보 수정 */
    @PostMapping(value = "/userInfoUpdate.json")
    @ResponseBody
    public Object userInfoUpdate(@RequestBody DochaAdminUpdateUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("updateUserInfoRequest", userInfoRequest);

        userService.updateUserInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    /* 회원 면허정보 수정 */
    @PostMapping(value = "/userLicenseUpdate.json")
    @ResponseBody
    public Object userLicenseUpdate(@RequestBody DochaAdminUserInfoUserLicenseInfoRequest licenseInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("updateLicenseInfoRequest", licenseInfoRequest);

        userService.updateUserLicenseInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    /* 회원 정보 삭제 */
    @DeleteMapping(value = "/userInfoDelete.json")
    @ResponseBody
    public Object userInfoDelete(@RequestBody DochaAdminUserInfoUserLicenseInfoRequest licenseInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("updateLicenseInfoRequest", licenseInfoRequest);

        userService.deleteUserInfo(serviceMessage);

        return serviceMessage.addData("res", "success");
    }

    /* 회원사 리스트 */
    @GetMapping(value = "/rentshop")
    public String rentShopList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getRentShopList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/rentshop_list";
    }

    /* 회원사 전체 휴일 등록*/
    @GetMapping(value = "/rentshop/hoilday")
    public String rentShopRegisterHoildayView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "user/hoilday_registration";
    }

    /* 회원사 상세 페이지 */
    @GetMapping(value = "/rentshop/{rtIdx}")
    public String rentShopDetail(@PathVariable String rtIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("rtIdx", rtIdx);

        modelMap.addAllAttributes(serviceMessage);
        return "user/rentshop_detail";
    }

    /* 관리자 리스트 */
    @GetMapping(value = "/admin")
    public String adminList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getAdminList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/admin_list";
    }

    /* 권한 템플릿 리스트 */
    @GetMapping(value = "/template")
    public String authTemplates(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        userService.getAuthTemplates(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "user/auth_templates";
    }
}
