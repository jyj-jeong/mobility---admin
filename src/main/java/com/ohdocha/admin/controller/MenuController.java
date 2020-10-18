package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.menu.DochaAdminMenuRequest;
import com.ohdocha.admin.service.MenuService;
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

    // region [ 사이트 메뉴 화면 ]

    /* 사이트 - 메인 화면 */
    @GetMapping(value = "/site/main")
    public String siteMainView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_main";
    }

    /* 사이트 - 쿠폰 화면 */
    @GetMapping(value = "/site/coupon")
    public String siteCouponView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_coupon";
    }

    /* 사이트 - 포인트 화면 */
    @GetMapping(value = "/site/point")
    public String sitePointView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_point";
    }

    /* 사이트 - 이용후기 화면 */
    @GetMapping(value = "/site/review")
    public String siteReviewView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_review";
    }

    /* 사이트 - 고객평점 화면 */
    @GetMapping(value = "/site/rating")
    public String cuView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_rating";
    }

    /* 사이트 - 이벤트 화면 */
    @GetMapping(value = "/site/event")
    public String siteEventView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_event";
    }

    /* 사이트 - 문의 화면 */
    @GetMapping(value = "/site/question")
    public String siteQuestionView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_question";
    }

    /* 사이트 - 공지사항 화면 */
    @GetMapping(value = "/site/notice")
    public String siteNoticeView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "site/site_notice";
    }

    // endregion

    // region [ 통계 ]

    @GetMapping(value = "/stats")
    public String statisticsView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "statistics";
    }

    // endregion

    // region [ 로그 ]

    @GetMapping(value = "/log")
    public String logView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "log";
    }

    // endregion
}
