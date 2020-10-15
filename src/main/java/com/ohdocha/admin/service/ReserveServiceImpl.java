package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.reserve.reserveInfoMnt.*;
import com.ohdocha.admin.mapper.DochaAdminReserveInfoMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReserveServiceImpl extends ServiceExtension implements ReserveService{

    private final DochaAdminReserveInfoMapper reserveInfoMapper;

    @Override
    public void getReserveInfoList(ServiceMessage message) {
        DochaAdminReserveInfoRequest reserveInfoRequest = message.getObject("reserveInfoRequest", DochaAdminReserveInfoRequest.class);

        List<DochaAdminReserveInfoResponse> reserveInfoResponseList = reserveInfoMapper.selectReserveInfoList(reserveInfoRequest);

        message.addData("result", reserveInfoResponseList);
    }

    @Override
    public void getReserveInfo(ServiceMessage message) {
        DochaAdminReserveInfoRequest reserveInfoRequest = message.getObject("reserveInfoRequest", DochaAdminReserveInfoRequest.class);

        List<DochaAdminReserveInfoDetailResponse> reserveInfoDetailResponseList = reserveInfoMapper.selectReserveInfo(reserveInfoRequest);
        List<DochaAdminReserveInfoDetailResponse> reservePaymentResponseList = reserveInfoMapper.selectPaymentList(reserveInfoRequest);

        message.addData("result", reserveInfoDetailResponseList);
        message.addData("paymentList", reservePaymentResponseList);
    }

    @Override
    public void selectCompanyInfo(ServiceMessage message) {
        DochaAdminReserveInfoRequest reserveInfoRequest = message.getObject("reserveInfoRequest", DochaAdminReserveInfoRequest.class);

        DochaRentCompanyDto rentCompanyDto = reserveInfoMapper.selectCompanyInfo(reserveInfoRequest);

        message.addData("companyInfo", rentCompanyDto);
    }

    @Override
    public void selectCompanyInfoAndCarInfo(ServiceMessage message) {
        DochaAdminReserveInfoRequest reserveInfoRequest = message.getObject("reserveInfoRequest", DochaAdminReserveInfoRequest.class);

        List<DochaCarDto> carDtoList = reserveInfoMapper.selectCompanyInfoAndCarInfo(reserveInfoRequest);

        message.addData("carList", carDtoList);
    }
}
