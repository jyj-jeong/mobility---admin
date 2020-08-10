package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.member.CsdealAdminCdtUserInfoRequest;
import com.ohdocha.admin.domain.member.CsdealAdminCdtUserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsdealAdminLoginMapper {
    public CsdealAdminCdtUserInfoResponse chkLoginUserInfo(CsdealAdminCdtUserInfoRequest requestDto);
}
