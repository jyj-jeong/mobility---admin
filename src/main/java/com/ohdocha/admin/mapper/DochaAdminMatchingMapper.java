package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.reserve.matchingService.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminMatchingMapper {

    // 매칭 리스트
    public List<DochaAdminMatchingResponse> selectMatchingServiceInfoList(DochaAdminMatchingRequest reqParam);

    // 회원 매칭 상세
    public List<DochaAdminMatchingDetailResponse> selectMatchingServiceInfo(DochaAdminMatchingDetailRequest reqParam);

    // 회원사 매칭 상세 리스트
    public List<DochaAdminMatchingCompanyResponse> selectMatchingCompanyServiceInfo(DochaAdminMatchingCompanyRequest reqParam);

    // 견적취소
    public int updateMatchingCompanyServiceInfo(DochaAdminMatchingCompanyRequest reqParam);

    // 견적 저장
    public int insertMatchingCompanyServiceInfo(DochaAdminMatchingCompanyRequest reqParam);

}
