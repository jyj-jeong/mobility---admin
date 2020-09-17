package com.ohdocha.admin.controller;

import com.ohdocha.admin.config.Properties;
import com.ohdocha.admin.domain.common.code.DochaAdminCommonCodeRequest;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoRequest;
import com.ohdocha.admin.service.MainService;
import com.ohdocha.admin.util.DochaMap;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@AllArgsConstructor
@Controller
public class MainController extends ControllerExtension {

    private final Properties properties;
    private final MainService mainService;

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginView(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Object adminObj = request.getSession().getAttribute("LOGIN_SESSION");

//        if (adminObj instanceof AdminInfoDto) {
//            String redirectUrl = getRequestParam(request, "redirectUrl");
//            if (!TextUtils.isEmpty(redirectUrl)) sendRedirect(response, redirectUrl);
//            else sendRedirect(response, request.getContextPath() + "/");
//        }

        modelMap.addAttribute("serverVersion", properties.getServerVersion());
        modelMap.addAttribute("serverName", properties.getServerName());

        return "login";
    }

    @PostMapping(value = "/api/v1.0/carssum.login")
    @ResponseBody
    public Object loginAsync(@RequestBody DochaAdminDcUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request).addData("userInfoRequest", userInfoRequest);
        serviceMessage.addData("userInfoRequest",userInfoRequest);

        mainService.login(serviceMessage);

        DochaMap userInfo = serviceMessage.getObject("userInfo", DochaMap.class);
        request.getSession().setAttribute("LOGIN_SESSION", userInfo);

        return serviceMessage;
    }

    @GetMapping(value = "/api/v1.0/carssum.logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().invalidate();
        sendRedirect(response, request.getContextPath() + "/login");

        return "login";
    }

    /* 공통 코드 리스트 */
    @PostMapping(value = "/api/v1.0/commonCodeInfo.json")
    @ResponseBody
    public Object userInfoDetail(@RequestBody DochaAdminCommonCodeRequest commonCodeRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("commonCodeRequest", commonCodeRequest);

        mainService.selectCommonCodeInfo(serviceMessage);

        return serviceMessage.get("result");
    }
}
