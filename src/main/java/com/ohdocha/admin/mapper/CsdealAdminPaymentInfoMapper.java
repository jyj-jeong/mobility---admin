package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.reserve.payment.CsdealAdminPaymentInfoRequest;
import com.ohdocha.admin.domain.reserve.payment.CsdealAdminPaymentInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminPaymentInfoMapper {
    public List<CsdealAdminPaymentInfoResponse> selectPaymentInfoList(CsdealAdminPaymentInfoRequest reqParam);

    public CsdealAdminPaymentInfoResponse selectPeriodPaymentInfo(CsdealAdminPaymentInfoRequest reqParam);

    public int insertPaymentDetail(CsdealAdminPaymentInfoRequest reqParam);
}
