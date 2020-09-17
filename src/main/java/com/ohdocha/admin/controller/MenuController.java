package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.menu.DochaAdminMenuRequest;
import com.ohdocha.admin.service.MenuService;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@AllArgsConstructor
public class MenuController extends ControllerExtension {

    private final MenuService menuService;

    /* 메뉴 리스트 조회 */
    @PostMapping(value = "/api/v1.0/menuInfoList.json")
    @ResponseBody
    public Object menuList(@RequestBody DochaAdminMenuRequest menuRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("menuRequest", menuRequest);

        menuService.getMenuList(serviceMessage);

        return serviceMessage.get("result");
    }
}
