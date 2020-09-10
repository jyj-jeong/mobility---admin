package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailResponse;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelRequest;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelResponse;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBasicPlanResponse;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateResponse;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarResponse;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaAdminReserveInfoRequest;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaRentCompanyDto;
import com.ohdocha.admin.mapper.*;
import com.ohdocha.admin.util.ServiceMessage;
import com.ohdocha.admin.util.TextUtils;
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
    private final DochaAdminReserveInfoMapper reserveInfoMapper;
    private final DochaAdminInsuranceTemplateMapper insuranceTemplateMapper;
    private final DochaAdminBasicPlanMapper basicPlanMapper;


    // region [ 등록차량 ]
    /* 등록차량 리스트 */
    @Override
    public void regCarList(ServiceMessage message) {
        DochaAdminRegCarRequest reqParam = message.getObject("reqParam", DochaAdminRegCarRequest.class);

        List<DochaAdminRegCarResponse> responseDto = regCarMapper.selectRegCarInfo(reqParam);

        message.addData("carRegList", responseDto);

    }

    /* 등록차량 추가 */
    @Override
    public void regCarAdd(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarRequest = message.getObject("regCarRequest", DochaAdminRegCarDetailRequest.class);

        regCarRequest.setCrIdx(createIdx());

        int res = regCarMapper.insertDcCarInfo(regCarRequest);

        message.addData("res", res);
        message.addData("crIdx", regCarRequest.getCrIdx());
    }

    /* 회사명(지점) 선택 */
    @Override
    public void companyList(ServiceMessage message) {
        DochaAdminReserveInfoRequest reqParam = message.getObject("reqParam", DochaAdminReserveInfoRequest.class);

        List<DochaRentCompanyDto> requestDto = reserveInfoMapper.selectCompanyList(reqParam);

        message.addData("result", requestDto);

    }

    /* 차종 선택 */
    @Override
    public void selectCarModelForSelectBox(ServiceMessage message) {
        DochaAdminCarModelDetailRequest reqParam = message.getObject("reqParam", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelResponses = carModelMapper.selectCarModelForSelectBox(reqParam);

        message.addData("result", carModelResponses);

    }

    /* 차종상세 선택 */
    @Override
    public void selectCarModelDetailForSelectBox(ServiceMessage message) {
        DochaAdminCarModelDetailRequest reqParam = message.getObject("reqParam", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelResponses = carModelMapper.selectCarModelDetailForSelectBox(reqParam);

        message.addData("result", carModelResponses);
    }

    /* 차량-요금제-기본요금제 상세 */
    @Override
    public void insuranceTemplateinfoDetail(ServiceMessage message) {
        DochaAdminInsuranceTemplateRequest reqParam = message.getObject("reqParam", DochaAdminInsuranceTemplateRequest.class);

        List<DochaAdminInsuranceTemplateResponse> responseDto = insuranceTemplateMapper.insuranceTemplateinfoDetail(reqParam);

        message.addData("result", responseDto);

    }

    /* 차량-요금제-기본요금제 리스트 */
    @Override
    public void basicPlanInfo(ServiceMessage message) {
        DochaAdminBaiscPlanRequest reqParam = message.getObject("reqParam", DochaAdminBaiscPlanRequest.class);

        List<DochaAdminBasicPlanResponse> responseDto = basicPlanMapper.selectBasicPlan(reqParam);

        message.addData("result", responseDto);

    }

    /* 차량-요금제-기본요금제 상세 */
    @Override
    public void basicPlanDetail(ServiceMessage message) {
        DochaAdminBaiscPlanRequest reqParam = message.getObject("reqParam", DochaAdminBaiscPlanRequest.class);

        List<DochaAdminBasicPlanResponse> responseDto = basicPlanMapper.selectBasicPlan(reqParam);

        message.addData("result", responseDto);
    }

    /* 등록차량 상세 옵션 */
    @Override
    public void selectRegCarDetailOption(ServiceMessage message) {
        DochaAdminRegCarDetailRequest reqParam = message.getObject("reqParam", DochaAdminRegCarDetailRequest.class);

        List<DochaAdminRegCarMapper> regCarMapperList = regCarMapper.selectRegCarDetailOption(reqParam);

        message.addData("result", regCarMapperList);
    }

    @Override
    public void updateCdtCarInfo(ServiceMessage message) {
        DochaAdminRegCarDetailRequest reqParam = message.getObject("regCarRequest", DochaAdminRegCarDetailRequest.class);

        int res = regCarMapper.updateDcCarInfo(reqParam);

    }

    /* 등록차량 보험 등록 */
    @Override
    public void insertRegCarInsurance(ServiceMessage message) {
        DochaAdminInsuranceTemplateDetailRequest insuranceTemplateRequest = message.getObject("insuranceTemplateRequest", DochaAdminInsuranceTemplateDetailRequest.class);

        int res = regCarMapper.insertRegCarInsurance(insuranceTemplateRequest);

        message.addData("res", res);
        message.addData("crIdx", insuranceTemplateRequest.getCrIdx());

    }

    /* 차량모델 등록 */
    @Override
    public void insertCarModelInfo(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        String mdIdx = TextUtils.getKeyDefault("MD");
        carModelDetailRequest.setMdIdx(mdIdx);

        int res = carModelMapper.insertCarModelInfo(carModelDetailRequest);

        message.addData("res", res);
        message.addData("mdIdx", carModelDetailRequest.getMdIdx());

    }

    // endregion

    /* 차량모델 리스트 */
    @Override
    public void getCarModelList(ServiceMessage message) {
        DochaAdminCarModelRequest reqParam = message.getObject("reqParam", DochaAdminCarModelRequest.class);

        List<DochaAdminCarModelResponse> responseDto = carModelMapper.selectCarModelInfo(reqParam);

        message.addData("carModelInfoList", responseDto);

    }

    /* 차량모델 상세 */
    @Override
    public void selectCarModelDetail(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelDetailResponseList = carModelMapper.selectCarModelDetail(carModelDetailRequest);

        message.addData("result", carModelDetailResponseList);
    }

    @Override
    public void regCarDetail(ServiceMessage message) {

    }
}
