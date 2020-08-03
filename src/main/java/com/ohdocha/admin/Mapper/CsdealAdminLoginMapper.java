package com.ohdocha.admin.Mapper;

import com.ohdocha.admin.domain.CsdealAdminCdtUserInfoRequest;
import com.ohdocha.admin.domain.CsdealAdminCdtUserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsdealAdminLoginMapper {
    public CsdealAdminCdtUserInfoResponse chkLoginUserInfo(CsdealAdminCdtUserInfoRequest requestDto);
}
