package com.ohdocha.admin.controller;

import com.ohdocha.admin.config.Properties;
import com.ohdocha.admin.domain.member.CsdealAdminCdtUserInfoRequest;
import com.ohdocha.admin.service.MainService;
import com.ohdocha.admin.util.ServiceMessage;
import com.ohdocha.admin.util.TextUtils;
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

    @PostMapping(value = "/login")
    @ResponseBody
    public Object loginAsync(@RequestBody CsdealAdminCdtUserInfoRequest userInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request).addData("userInfoRequest", userInfoRequest);
        mainService.login(serviceMessage);

//        adminInfoDto = serviceMessage.getObject("adminLoginInfo", AdminInfoDto.class);
//        request.getSession().setAttribute("LOGIN_SESSION", adminInfoDto);

        return createServiceMessage(request).addData("result", "success");
    }
}
