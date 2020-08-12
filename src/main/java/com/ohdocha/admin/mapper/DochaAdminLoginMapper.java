package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.user.DochaAdminCdtUserInfoRequest;
import com.ohdocha.admin.domain.user.DochaAdminCdtUserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DochaAdminLoginMapper {
    public DochaAdminCdtUserInfoResponse chkLoginUserInfo(DochaAdminCdtUserInfoRequest requestDto);
}
