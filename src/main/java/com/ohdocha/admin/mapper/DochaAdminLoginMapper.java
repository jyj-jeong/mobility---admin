package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminDcUserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DochaAdminLoginMapper {
    public DochaAdminDcUserInfoResponse chkLoginUserInfo(DochaAdminDcUserInfoRequest requestDto);
}
