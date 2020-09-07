package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaAdminReserveInfoRequest;
import com.ohdocha.admin.service.CarService;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(value = "/car")
public class CarController extends ControllerExtension {

    private final CarService carService;

    /* 등록차량 리스트 */
    @GetMapping(value = "")
    public String carList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_list";
    }

    /* 차량 추가 */
    @GetMapping(value = "/add")
    public String regCarAdd(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);

        return "car/regCar";
    }

    @PostMapping(value = "/api/v1.0/insertDcCarInfo.do")
    @ResponseBody
    public Object regCarAdd(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("regCarRequest", regCarDetailRequest);

        carService.regCarAdd(serviceMessage);

        return serviceMessage;
    }

    @PostMapping(value = "/api/v1.0/updateDcCarInfo.do")
    @ResponseBody
    public Object updateCdtCarInfo(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("regCarRequest", regCarDetailRequest);

        carService.updateCdtCarInfo(serviceMessage);

        return serviceMessage;
    }

    /* 회사 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCompanyList.json")
    @ResponseBody
    public Object selectCompanyList(@RequestBody DochaAdminReserveInfoRequest dochaAdminReserveInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("dochaAdminReserveInfoRequest", dochaAdminReserveInfoRequest);

        carService.companyList(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차종 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCarModelForSelectBox.json")
    @ResponseBody
    public Object selectCarModelForSelectBox(@RequestBody DochaAdminCarModelDetailRequest dochaAdminCarModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("dochaAdminCarModelDetailRequest", dochaAdminCarModelDetailRequest);

        carService.selectCarModelForSelectBox(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차종상세 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCarModelDetailForSelectBox.json")
    @ResponseBody
    public Object selectCarModelDetailForSelectBox(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("dochaAdminReserveInfoRequest", carModelDetailRequest);

        carService.selectCarModelDetailForSelectBox(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차량-요금제-기본요금제 상세 */
    @PostMapping(value = "/api/v1.0/insuranceTemplateinfoDetail.json")
    @ResponseBody
    public Object insuranceTemplateinfoDetail(@RequestBody DochaAdminInsuranceTemplateRequest templateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", templateRequest);

        carService.insuranceTemplateinfoDetail(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차량-요금제-기간요금설정 List 조회 */
    @PostMapping(value = "/api/v1.0/basicPlanInfo.json")
    @ResponseBody
    public Object basicPlanInfo(@RequestBody DochaAdminInsuranceTemplateRequest templateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", templateRequest);

        carService.basicPlanInfo(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차량-요금제-기본요금제 상세 */
    @PostMapping(value = "/api/v1.0/basicPlanDetail.json")
    @ResponseBody
    public Object basicPlanDetail(@RequestBody DochaAdminBaiscPlanDetailRequest templateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", templateRequest);

        carService.basicPlanDetail(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 등록차량 상세 옵션 리스트 상세 */
    @PostMapping(value = "/api/v1.0/regCarDetailOption.json")
    @ResponseBody
    public Object selectRegCarDetailOption(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", regCarDetailRequest);

        carService.selectRegCarDetailOption(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 차량 관제 */
    @GetMapping(value = "/control")
    public String carControlList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_control_list";
    }

    /* 등록차량 리스트 */
    @GetMapping(value = "/maintain")
    public String carMaintainList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_maintain_list";
    }

    /* 차량모델 리스트 */
    @GetMapping(value = "/model")
    public String carModelList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_model_list";
    }

    @GetMapping(value = "/model/add")
    public String insertCarModelInfo(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);

        return "car/carModel";
    }

    /* 차량속성 : 국가 */
    @GetMapping(value = "/property/country")
    public String carCountryProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_country";
    }

    /* 차량속성 : 제조사 */
    @GetMapping(value = "/property/manufacturer")
    public String carManufacturerProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_manufacturer";
    }

    /* 차량속성 : 등급 */
    @GetMapping(value = "/property/cartype")
    public String carTypeProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_cartype";
    }

    /* 차량속성 : 옵션 */
    @GetMapping(value = "/property/option")
    public String carOptionProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_option";
    }

    /* 차량속성 : 연료 */
    @GetMapping(value = "/property/fuel")
    public String carFuelProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_fuel";
    }

    /* 요금제 */
    @GetMapping(value = "/payment")
    public String carPayment(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_payment";
    }
}
