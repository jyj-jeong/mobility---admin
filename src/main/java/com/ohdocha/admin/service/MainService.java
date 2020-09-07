package com.ohdocha.admin.service;

import com.ohdocha.admin.util.ServiceMessage;

public interface MainService {

    void login(ServiceMessage message);

    void selectCommonCodeInfo(ServiceMessage message);
}
