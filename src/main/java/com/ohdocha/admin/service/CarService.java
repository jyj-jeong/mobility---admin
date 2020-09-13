package com.ohdocha.admin.service;

import com.ohdocha.admin.util.ServiceMessage;

public interface CarService {

    void regCarList(ServiceMessage message);

    void getCarModelList(ServiceMessage message);

    void regCarAdd(ServiceMessage message);

    void companyList(ServiceMessage message);

    void selectCarModelForSelectBox(ServiceMessage message);

    void selectCarModelDetailForSelectBox(ServiceMessage message);

    void insuranceTemplateinfoDetail(ServiceMessage message);

    void basicPlanInfo(ServiceMessage message);

    void basicPlanDetail(ServiceMessage message);

    void selectRegCarDetailOption(ServiceMessage message);

    void updateCdtCarInfo(ServiceMessage message);

    void insertRegCarInsurance(ServiceMessage message);

    void insertCarModelInfo(ServiceMessage message);

    void selectCarModelDetail(ServiceMessage message);

    void updateCarModelInfo(ServiceMessage message);

    void regCarDetail(ServiceMessage serviceMessage);

    void carCountryProperty(ServiceMessage message);

    void deleteProperty(ServiceMessage message);

    void insertCarPropertyCountry(ServiceMessage message);

    void carManufacturerProperty(ServiceMessage message);

    void insertCarPropertyManufacturer(ServiceMessage message);

    void insertCarPropertyCarType(ServiceMessage message);

    void carCarTypeProperty(ServiceMessage message);

    void insertCarPropertyOption(ServiceMessage message);

    void carOptionProperty(ServiceMessage message);

    void carFuelProperty(ServiceMessage message);

    void insertCarPropertyFuel(ServiceMessage message);
}
