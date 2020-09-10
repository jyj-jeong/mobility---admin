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

    void regCarDetail(ServiceMessage message);
}
