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
