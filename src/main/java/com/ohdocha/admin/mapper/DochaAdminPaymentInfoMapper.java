package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.reserve.payment.DochaAdminPaymentInfoRequest;
import com.ohdocha.admin.domain.reserve.payment.DochaAdminPaymentInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminPaymentInfoMapper {
    public List<DochaAdminPaymentInfoResponse> selectPaymentInfoList(DochaAdminPaymentInfoRequest reqParam);

    public DochaAdminPaymentInfoResponse selectPeriodPaymentInfo(DochaAdminPaymentInfoRequest reqParam);

    public int insertPaymentDetail(DochaAdminPaymentInfoRequest reqParam);
}
