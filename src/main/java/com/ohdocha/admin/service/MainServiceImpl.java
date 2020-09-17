package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.common.code.DochaAdminCommonCodeRequest;
import com.ohdocha.admin.domain.common.code.DochaAdminCommonCodeResponse;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoResponse;
import com.ohdocha.admin.exception.BadRequestException;
import com.ohdocha.admin.mapper.DochaAdminCommonCodeMapper;
import com.ohdocha.admin.mapper.DochaAdminLoginMapper;
import com.ohdocha.admin.util.DochaMap;
import com.ohdocha.admin.util.ServiceMessage;
import com.ohdocha.admin.util.TextUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MainServiceImpl extends ServiceExtension implements MainService {

    private final DochaAdminLoginMapper loginMapper;
    private final DochaAdminCommonCodeMapper commonCodeMapper;

    @Override
    public void login(ServiceMessage message) {
        DochaAdminDcUserInfoRequest userInfoRequest = message.getObject("userInfoRequest", DochaAdminDcUserInfoRequest.class);

        DochaAdminDcUserInfoResponse userInfoResponse = loginMapper.chkLoginUserInfo(userInfoRequest);
        if (userInfoResponse != null){

            DochaMap userInfo = new DochaMap();
            userInfo.set("urIdx", userInfoResponse.getUrIdx());
            userInfo.set("userName", userInfoResponse.getUserName());
            userInfo.set("userRole", userInfoResponse.getUserRole());
            userInfo.set("rtIdx", userInfoResponse.getRtIdx());
            userInfo.set("companyName", userInfoResponse.getCompanyName());
            userInfo.set("branchName", userInfoResponse.getBranchName());

            message.addData("userInfo", userInfo);
        }else {
            message.addData("err", "error");
            throw new BadRequestException(500,"로그인 실패");
        }
    }

    @Override
    public void selectCommonCodeInfo(ServiceMessage message) {
        DochaAdminCommonCodeRequest commonCodeRequest = message.getObject("commonCodeRequest", DochaAdminCommonCodeRequest.class);

        List<DochaAdminCommonCodeResponse> commonCodeResponseList = commonCodeMapper.selectCommonCodeInfo(commonCodeRequest);

        message.addData("result", commonCodeResponseList);
    }
}
