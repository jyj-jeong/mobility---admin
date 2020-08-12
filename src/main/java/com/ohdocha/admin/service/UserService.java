package com.ohdocha.admin.service;

import com.ohdocha.admin.util.ServiceMessage;

public interface UserService {

    void getIntegratedUserList(ServiceMessage message);

    void getUserDetail(ServiceMessage message);

    void getUserLicenseInfo(ServiceMessage message);

    void updateUserInfo(ServiceMessage message);

    void updateUserLicenseInfo(ServiceMessage message);

    void deleteUserInfo(ServiceMessage message);

    void getRentShopList(ServiceMessage message);

    void getAdminList(ServiceMessage message);

    void getAuthTemplates(ServiceMessage message);

}
