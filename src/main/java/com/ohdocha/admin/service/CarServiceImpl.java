package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelRequest;
import com.ohdocha.admin.domain.car.model.CsdealAdminCarModelResponse;
import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.CsdealAdminRegCarResponse;
import com.ohdocha.admin.mapper.CsdealAdminCarModelMapper;
import com.ohdocha.admin.mapper.CsdealAdminRegCarMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarServiceImpl extends ServiceExtension implements CarService {

    private final CsdealAdminCarModelMapper carModelMapper;
    private final CsdealAdminRegCarMapper regCarMapper;

    @Override
    public void getCarList(ServiceMessage message) {
        CsdealAdminRegCarRequest reqParam = message.getObject("reqParam", CsdealAdminRegCarRequest.class);

        List<CsdealAdminRegCarResponse> responseDto = regCarMapper.selectRegCarInfo(reqParam);

        message.addData("carRegList", responseDto);

    }

    @Override
    public void getCarModelList(ServiceMessage message) {
        CsdealAdminCarModelRequest reqParam = message.getObject("reqParam", CsdealAdminCarModelRequest.class);

        List<CsdealAdminCarModelResponse> responseDto = carModelMapper.selectCarModelInfo(reqParam);

        message.addData("carModelInfoList", responseDto);


    }
}
