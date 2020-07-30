package com.ohdocha.admin.controller;

import com.ohdocha.admin.service.MemberService;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = "/mem")
public class MemberController extends ControllerExtension {

    private final MemberService memberService;

    /* 통합 회원 리스트 */
    @GetMapping(value = "")
    public String memberList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        memberService.getIntegratedMemberList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "member/integrated_member_list";
    }

    /* 회원 상세 페이지 */
    @GetMapping(value = "/detail")
    public String memberDetail(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "member/integrated_member_list";
    }

    /* 회원사 리스트 */
    @GetMapping(value = "/rentshop")
    public String rentShop(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "member/rentshop_list";
    }

    /* 관리자 리스트 */
    @GetMapping(value = "/admin")
    public String adminList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "member/admin_list";
    }

    /* 권한 템플릿 리스트 */
    @GetMapping(value = "/template")
    public String permissionTemplates(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "member/permission_templates";
    }
}
