package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaAdminReserveInfoRequest;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaAdminReserveInfoResponse;
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

        message.addData("reserveInfoResponseList", reserveInfoResponseList);
    }
}
