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

    @GetMapping(value = "/model")
    public String carModelList(HttpServletRequest request, ModelMap modelMap) {
        ServiceMessage serviceMessage = createServiceMessage(request);
        carService.getCarModelInfo(serviceMessage);

        modelMap.addAllAttributes(serviceMessage);
        return "car/car_model_list";
    }
}
