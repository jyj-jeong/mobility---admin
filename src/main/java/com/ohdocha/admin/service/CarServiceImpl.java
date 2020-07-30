package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.CarModelInfo;
import com.ohdocha.admin.domain.CarModelInfoRepository;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarServiceImpl extends ServiceExtension implements CarService {

    private CarModelInfoRepository carModelInfoRepository;

    @Override
    public void getCarModelInfo(ServiceMessage message) {

        List<CarModelInfo> carModelInfoList = carModelInfoRepository.findAll();

        message.addData("carModelInfoList", carModelInfoList);
    }
}
