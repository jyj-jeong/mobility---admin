package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.reserve.payment.DochaAdminPaymentInfoRequest;
import com.ohdocha.admin.domain.reserve.payment.DochaAdminPaymentInfoResponse;
import com.ohdocha.admin.mapper.DochaAdminPaymentInfoMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentInfoServiceImpl extends ServiceExtension implements PaymentInfoService {

    DochaAdminPaymentInfoMapper paymentInfoMapper;

    @Override
    public void paymentInfoList(ServiceMessage message) {
        DochaAdminPaymentInfoRequest reqParam = message.getObject("reqParam", DochaAdminPaymentInfoRequest.class);

        List<DochaAdminPaymentInfoResponse> paymentInfoList = paymentInfoMapper.selectPaymentInfoList(reqParam);

        message.addData("paymentInfoList", paymentInfoList);

    }
}
