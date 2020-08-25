package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelRequest;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelResponse;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarResponse;
import com.ohdocha.admin.mapper.DochaAdminCarModelMapper;
import com.ohdocha.admin.mapper.DochaAdminRegCarMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarServiceImpl extends ServiceExtension implements CarService {

    private final DochaAdminCarModelMapper carModelMapper;
    private final DochaAdminRegCarMapper regCarMapper;

    @Override
    public void getCarList(ServiceMessage message) {
        DochaAdminRegCarRequest reqParam = message.getObject("reqParam", DochaAdminRegCarRequest.class);

        List<DochaAdminRegCarResponse> responseDto = regCarMapper.selectRegCarInfo(reqParam);

        message.addData("carRegList", responseDto);

    }

    @Override
    public void regCarAdd(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarRequest = message.getObject("regCarRequest", DochaAdminRegCarDetailRequest.class);

        int res = regCarMapper.insertDcCarInfo(regCarRequest);
    }

    @Override
    public void getCarModelList(ServiceMessage message) {
        DochaAdminCarModelRequest reqParam = message.getObject("reqParam", DochaAdminCarModelRequest.class);

        List<DochaAdminCarModelResponse> responseDto = carModelMapper.selectCarModelInfo(reqParam);

        message.addData("carModelInfoList", responseDto);


    }
}
