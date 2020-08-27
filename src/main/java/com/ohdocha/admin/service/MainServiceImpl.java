package com.ohdocha.admin.service;

import com.ohdocha.admin.mapper.DochaAdminLoginMapper;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoResponse;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MainServiceImpl extends ServiceExtension implements MainService {

    private final DochaAdminLoginMapper mapper;

    @Override
    public void login(ServiceMessage message) {
        DochaAdminDcUserInfoRequest userInfoRequest = message.getObject("userInfoRequest", DochaAdminDcUserInfoRequest.class);

        DochaAdminDcUserInfoResponse userInfoResponse = mapper.chkLoginUserInfo(userInfoRequest);

//        AdminInfo adminInfo = memberOptional.get();
//        if (TextUtils.isEmpty(adminInfo.getAdminPassword()))
//            throw new RestUnauthorizedException(UNKNOWN_EXCEPTION, UNKNOWN_EXCEPTION_MSG);
//
//        String DbAdminPassword = EncryptHelper.getInstance().setAlgorithm(EncryptHelper.Algorithm.SHA256).encrypt(adminInfo.getAdminPassword());
//        if (!adminInfoDto.getAdminPassword().equals(DbAdminPassword))
//            throw new RestUnauthorizedException(ADMIN_PASSWORD_MISMATCH, ADMIN_PASSWORD_MISMATCH_MSG);
//
//        adminInfoDto = adminInfo.toDto();
//        adminInfoDto.setAdminName(adminInfo.getAdminName());
//        adminInfoDto.setAdminPassword("");
//
//        message.addData("adminLoginInfo", adminInfoDto);
    }
}