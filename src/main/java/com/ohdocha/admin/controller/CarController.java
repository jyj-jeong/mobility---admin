package com.ohdocha.admin.controller;

import com.ohdocha.admin.service.CarService;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(value = "/car")
public class CarController extends ControllerExtension {

    private final CarService carService;

    @GetMapping(value = "")
    public String carList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_list";
    }

    @GetMapping(value = "/control")
    public String carControlList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_control_list";
    }

    @GetMapping(value = "/maintain")
    public String carMaintainList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_maintain_list";
    }

    @GetMapping(value = "/model")
    public String carModelList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_model_list";
    }

    @GetMapping(value = "/property")
    public String carProperty(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_property_list";
    }

    @GetMapping(value = "/payment")
    public String carPayment(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelList(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_payment";
    }
}
