package com.ohdocha.admin.controller;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.property.DochaAdminCarPropertyRequest;
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
public class CarController extends ControllerExtension {

    private final CarService carService;

    //region [ 등록차량 ]
    /* 등록차량 리스트 */
    @GetMapping(value = "/car")
    public String regCarList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.regCarList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_list";
    }

    /* 등록차량 추가 화면 */
    @GetMapping(value = "/car/add")
    public String regCarAddView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("CRUD","insert");

        modelMap.addAllAttributes(serviceMessage);

        return "car/regCar";
    }

    /* 등록차량 추가 */
    @PostMapping(value = "/api/v1.0/insertDcCarInfo.do")
    @ResponseBody
    public Object regCarAdd(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("regCarDetailRequest", regCarDetailRequest);

        carService.regCarAdd(serviceMessage);

        return serviceMessage;
    }

    /* 등록차량 보험 추가 */
    @PostMapping(value = "/api/v1.0/insertDcCarInsuranceInfo.do")
    @ResponseBody
    public Object insertRegCarInsurance(@RequestBody DochaAdminInsuranceTemplateDetailRequest insuranceTemplateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("insuranceTemplateRequest", insuranceTemplateRequest);

        carService.insertRegCarInsurance(serviceMessage);

        return serviceMessage;
    }

    /* 등록차량 상세 화면 */
    @GetMapping(value = "/car/detail/{crIdx}")
    public String regCarDetailView(@PathVariable String crIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("crIdx", crIdx);
        serviceMessage.addData("CRUD", "modify");

        modelMap.addAllAttributes(serviceMessage);
        return "car/regCar";
    }

    /* 등록차량 상세  */
    @PostMapping(value = "/api/v1.0/regCarDetail.json")
    @ResponseBody
    public Object regCarDetail(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("regCarDetailRequest", regCarDetailRequest);

        carService.regCarDetail(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 등록차량 수정 */
    @PostMapping(value = "/api/v1.0/updateDcCarInfo.do")
    @ResponseBody
    public Object updateCdtCarInfo(@RequestBody DochaAdminRegCarDetailRequest regCarDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("regCarRequest", regCarDetailRequest);

        carService.updateCdtCarInfo(serviceMessage);

        return serviceMessage;
    }



    //region [ 등록차량 옵션 선택 ]
    /* 회사 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCompanyList.json")
    @ResponseBody
    public Object selectCompanyList(@RequestBody DochaAdminReserveInfoRequest reserveInfoRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("reserveInfoRequest", reserveInfoRequest);

        carService.companyList(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차종 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCarModelForSelectBox.json")
    @ResponseBody
    public Object selectCarModelForSelectBox(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.selectCarModelForSelectBox(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 차종상세 옵션 선택 */
    @PostMapping(value = "/api/v1.0/selectCarModelDetailForSelectBox.json")
    @ResponseBody
    public Object selectCarModelDetailForSelectBox(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.selectCarModelDetailForSelectBox(serviceMessage);

        return serviceMessage.get("result");

    }

    /* 보험 템플릿 선택 */
    @PostMapping(value = "/api/v1.0/insuranceTemplateinfoDetail.json")
    @ResponseBody
    public Object insuranceTemplateinfoDetail(@RequestBody DochaAdminInsuranceTemplateRequest templateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", templateRequest);

        carService.insuranceTemplateinfoDetail(serviceMessage);

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
    //endregion
    //endregion





    //region [ 차량 모델 ]
    /* 차량모델 등록 화면 */
    @GetMapping(value = "/car/model/add")
    public String insertCarModelInfoView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("CRUD", "insert");

        modelMap.addAllAttributes(serviceMessage);

        return "car/carModel";
    }

    /* 차량모델 등록 */
    @PostMapping(value = "/api/v1.0/insertCarModelInfo.do")
    @ResponseBody
    public Object insertCarModelInfo(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.insertCarModelInfo(serviceMessage);

        return serviceMessage;
    }

    /* 차량 모델 리스트 */
    @GetMapping(value = "/car/model")
    public String carModelList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_model_list";
    }

    /* 차량모델 상세 화면 */
    @GetMapping(value = "/car/model/{mdIdx}")
    public String carModelDetailView(@PathVariable String mdIdx, HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("mdIdx", mdIdx);
        serviceMessage.addData("CRUD", "modify");

        modelMap.addAllAttributes(serviceMessage);
        return "car/carModel";
    }

    /* 차량모델 상세  */
    @PostMapping(value = "/api/v1.0/carModelDetail.json")
    @ResponseBody
    public Object carModelDetail(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.selectCarModelDetail(serviceMessage);

        return serviceMessage.get("result");
    }

    /* 차량모델 수정 */
    @PostMapping(value = "/api/v1.0/updateCarModelInfo.do")
    @ResponseBody
    public Object updateCarModelInfo(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.updateCarModelInfo(serviceMessage);

        return serviceMessage;
    }


    //endregion





    //region [ 차량 속성 ]
    /* 차량속성 : 국가 등록 */
    @PostMapping(value = "/car/property/country/{value}")
    @ResponseBody
    public Object insertCarPropertyCountry(@PathVariable String value, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("value", value);

        carService.insertCarPropertyCountry(serviceMessage);

        return serviceMessage;
    }

    /* 차량속성 : 국가 리스트 */
    @GetMapping(value = "/car/property/country")
    public String carCountryProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        carService.carCountryProperty(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_country";
    }

    /* 차량속성 : 제조사 등록 */
    @PostMapping(value = "/car/property/manufacturer/{value}")
    @ResponseBody
    public Object insertCarPropertyManufacturer(@PathVariable String value, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("value", value);

        carService.insertCarPropertyManufacturer(serviceMessage);

        return serviceMessage;
    }

    /* 차량속성 : 제조사 리스트 */
    @GetMapping(value = "/car/property/manufacturer")
    public String carManufacturerProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        carService.carManufacturerProperty(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_manufacturer";
    }

    /* 차량속성 : 등급 등록 */
    @PostMapping(value = "/car/property/cartype/{value}")
    @ResponseBody
    public Object insertCarPropertyCarType(@PathVariable String value, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("value", value);

        carService.insertCarPropertyCarType(serviceMessage);

        return serviceMessage;
    }

    /* 차량속성 : 등급 리스트 */
    @GetMapping(value = "/car/property/cartype")
    public String carCarTypeProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        carService.carCarTypeProperty(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_cartype";
    }

    /* 차량속성 : 옵션 등록 */
    @PostMapping(value = "/car/property/option/{value}")
    @ResponseBody
    public Object insertCarPropertyOption(@PathVariable String value, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("value", value);

        carService.insertCarPropertyOption(serviceMessage);

        return serviceMessage;
    }

    /* 차량속성 : 옵션 리스트 */
    @GetMapping(value = "/car/property/option")
    public String carOptionProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        carService.carOptionProperty(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_option";
    }

    /* 차량속성 : 연료 등록 */
    @PostMapping(value = "/car/property/fuel/{value}")
    @ResponseBody
    public Object insertCarPropertyFuel(@PathVariable String value, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("value", value);

        carService.insertCarPropertyFuel(serviceMessage);

        return serviceMessage;
    }

    /* 차량속성 : 연료 리스트 */
    @GetMapping(value = "/car/property/fuel")
    public String carFuelProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        carService.carFuelProperty(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/property/car_property_fuel";
    }

    /* 속성 삭제 */
    @DeleteMapping(value = "/car/property/{codeIdx}")
    @ResponseBody
    public Object deleteCarProperty(@PathVariable String codeIdx, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request)
                .addData("codeIdx", codeIdx);

        carService.deleteProperty(serviceMessage);

        return "{\"result\":\"success\"}";
    }
    //endregion





    //region [ 요금제 ]
    /* 기간요금제 등록 화면 */
    @GetMapping(value = "/car/payment/period/add")
    public String insertPeriodPlanInfoView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("CRUD", "insert");

        modelMap.addAllAttributes(serviceMessage);

        return "car/payment/periodPlan";
    }

    /* 기간요금제 등록 */
    @PostMapping(value = "/api/v1.0/insertInfo.do")
    @ResponseBody
    public Object insertPeriodPlanInfo(@RequestBody DochaAdminCarModelDetailRequest carModelDetailRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("carModelDetailRequest", carModelDetailRequest);

        carService.insertCarModelInfo(serviceMessage);

        return serviceMessage;
    }

    /* 기간 요금제 리스트 */
    @GetMapping(value = "/car/payment/period")
    public String periodPlanView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "car/payment/periodPlan_list";
    }

    /* 기본 요금제 화면 */
    @GetMapping(value = "/car/payment/basic")
    public String basicPlanView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "car/payment/basicPlan_list";
    }

    /* 보험 템플릿 화면 */
    @GetMapping(value = "/car/payment/insurance")
    public String insuranceTemplateView(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);

        modelMap.addAllAttributes(serviceMessage);
        return "car/payment/basicPlan_list";
    }
    //endregion






    /* 차량-요금제-기간요금설정 List 조회 */
    @PostMapping(value = "/api/v1.0/basicPlanInfo.json")
    @ResponseBody
    public Object basicPlanInfo(@RequestBody DochaAdminInsuranceTemplateRequest templateRequest, HttpServletRequest request) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        serviceMessage.addData("templateRequest", templateRequest);

        carService.basicPlanInfo(serviceMessage);

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










    //region [ 메뉴 준비 중.. ]
    /* 차량 관제 */
    @GetMapping(value = "/car/control")
    public String carControlList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_control_list";
    }

    /* 차량정비 */
    @GetMapping(value = "/car/maintain")
    public String carMaintainList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_maintain_list";
    }
    //endregion
}
