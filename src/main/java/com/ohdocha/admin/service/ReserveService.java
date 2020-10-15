package com.ohdocha.admin.service;

import com.ohdocha.admin.util.ServiceMessage;

public interface ReserveService {

    void getReserveInfoList(ServiceMessage serviceMessage);

    void getReserveInfo(ServiceMessage serviceMessage);

    void selectCompanyInfo(ServiceMessage serviceMessage);

    void selectCompanyInfoAndCarInfo(ServiceMessage serviceMessage);

}
