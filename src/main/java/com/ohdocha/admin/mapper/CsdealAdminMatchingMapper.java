package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.reserve.matchingService.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminMatchingMapper {

    // 매칭 리스트
    public List<CsdealAdminMatchingResponse> selectMatchingServiceInfoList(CsdealAdminMatchingRequest reqParam);

    // 회원 매칭 상세
    public List<CsdealAdminMatchingDetailResponse> selectMatchingServiceInfo(CsdealAdminMatchingDetailRequest reqParam);

    // 회원사 매칭 상세 리스트
    public List<CsdealAdminMatchingCompanyResponse> selectMatchingCompanyServiceInfo(CsdealAdminMatchingCompanyRequest reqParam);

    // 견적취소
    public int updateMatchingCompanyServiceInfo(CsdealAdminMatchingCompanyRequest reqParam);

    // 견적 저장
    public int insertMatchingCompanyServiceInfo(CsdealAdminMatchingCompanyRequest reqParam);

}
