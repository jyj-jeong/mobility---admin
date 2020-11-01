package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailRequest;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelDetailResponse;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelRequest;
import com.ohdocha.admin.domain.car.model.DochaAdminCarModelResponse;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanDetailRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBaiscPlanRequest;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBasicPlanDetailResponse;
import com.ohdocha.admin.domain.car.plan.basicplan.DochaAdminBasicPlanResponse;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateDetailRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateRequest;
import com.ohdocha.admin.domain.car.plan.insuranceTemplate.DochaAdminInsuranceTemplateResponse;
import com.ohdocha.admin.domain.car.plan.periodplansetting.DochaAdminPeriodPlanSettingDetailRequest;
import com.ohdocha.admin.domain.car.plan.periodplansetting.DochaAdminPeriodPlanSettingDetailResponse;
import com.ohdocha.admin.domain.car.plan.periodplansetting.DochaAdminPeriodPlanSettingRequest;
import com.ohdocha.admin.domain.car.plan.periodplansetting.DochaAdminPeriodPlanSettingResponse;
import com.ohdocha.admin.domain.car.property.DochaAdminCarPropertyRequest;
import com.ohdocha.admin.domain.car.property.DochaAdminCarPropertyResponse;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarDetailResponse;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarRequest;
import com.ohdocha.admin.domain.car.regcar.DochaAdminRegCarResponse;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaAdminReserveInfoRequest;
import com.ohdocha.admin.domain.reserve.reserveInfoMnt.DochaRentCompanyDto;
import com.ohdocha.admin.mapper.*;
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
    private final DochaAdminReserveInfoMapper reserveInfoMapper;
    private final DochaAdminInsuranceTemplateMapper insuranceTemplateMapper;
    private final DochaAdminBasicPlanMapper basicPlanMapper;
    private final DochaAdminCarPropertyMapper propertyMapper;
    private final DochaAdminPeriodPlanSettingMapper periodPlanSettingMapper;


    // region [ 등록차량 ]
    /* 등록차량 리스트 */
    @Override
    public void regCarList(ServiceMessage message) {
        DochaAdminRegCarRequest regCarRequest = new DochaAdminRegCarRequest();

        List<DochaAdminRegCarResponse> responseDto = regCarMapper.selectRegCarInfo(regCarRequest);

        message.addData("carRegList", responseDto);
    }

    /* 등록차량 추가 */
    @Override
    public void regCarAdd(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarDetailRequest = message.getObject("regCarDetailRequest", DochaAdminRegCarDetailRequest.class);

        regCarDetailRequest.setCrIdx("CR" + createIdx());

        int res = regCarMapper.insertDcCarInfo(regCarDetailRequest);

        message.addData("res", res);
        message.addData("crIdx", regCarDetailRequest.getCrIdx());
    }

    /* 등록차량 보험 추가 */
    @Override
    public void insertRegCarInsurance(ServiceMessage message) {
        DochaAdminInsuranceTemplateDetailRequest insuranceTemplateRequest = message.getObject("insuranceTemplateRequest", DochaAdminInsuranceTemplateDetailRequest.class);

        int res = regCarMapper.insertRegCarInsurance(insuranceTemplateRequest);

        message.addData("res", res);
        message.addData("crIdx", insuranceTemplateRequest.getCrIdx());
    }

    /* 등록차량 요금제 추가 */
    @Override
    public void insertRegCarPayment(ServiceMessage message) {
        DochaAdminBaiscPlanDetailRequest paymentInfoRequest = message.getObject("paymentInfoRequest", DochaAdminBaiscPlanDetailRequest.class);

        int res = regCarMapper.insertRegCarPayment(paymentInfoRequest);

        message.addData("res", res);
        message.addData("crIdx", paymentInfoRequest.getCrIdx());
    }

    /* 등록차량 상세 */
    @Override
    public void regCarDetail(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarDetailRequest = message.getObject("regCarDetailRequest", DochaAdminRegCarDetailRequest.class);

        List<DochaAdminRegCarDetailResponse> regCarDetailResponseList = regCarMapper.selectRegCarDetail(regCarDetailRequest);

        message.addData("result", regCarDetailResponseList);
    }

    /* 등록차량 요금 계산 */
    @Override
    public void selectReserveAmt(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarDetailRequest = message.getObject("regCarDetailRequest", DochaAdminRegCarDetailRequest.class);

        List<DochaAdminRegCarDetailResponse> regCarDetailResponseList = regCarMapper.selectReserveAmt(regCarDetailRequest);

        message.addData("result", regCarDetailResponseList);
    }

    /* 등록차량 수정 */
    @Override
    public void updateCdtCarInfo(ServiceMessage message) {
        DochaAdminRegCarDetailRequest regCarRequest = message.getObject("regCarRequest", DochaAdminRegCarDetailRequest.class);

        int res = regCarMapper.updateDcCarInfo(regCarRequest);

        message.addData("res", res);

    }


    //region [ 등록차량 옵션 선택]
    /* 회사 옵션 선택 */
    @Override
    public void companyList(ServiceMessage message) {
        DochaAdminReserveInfoRequest reserveInfoRequest = message.getObject("reserveInfoRequest", DochaAdminReserveInfoRequest.class);

        List<DochaRentCompanyDto> rentCompanyDtoList = reserveInfoMapper.selectCompanyList(reserveInfoRequest);

        message.addData("result", rentCompanyDtoList);

    }

    /* 차종 옵션 선택 */
    @Override
    public void selectCarModelForSelectBox(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelDetailResponseList = carModelMapper.selectCarModelForSelectBox(carModelDetailRequest);

        message.addData("result", carModelDetailResponseList);

    }

    /* 차종상세 옵션 선택 */
    @Override
    public void selectCarModelDetailForSelectBox(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelDetailResponseList = carModelMapper.selectCarModelDetailForSelectBox(carModelDetailRequest);

        message.addData("result", carModelDetailResponseList);
    }

    /* 보험 템플릿 선택 */
    @Override
    public void insuranceTemplateinfoDetail(ServiceMessage message) {
        DochaAdminInsuranceTemplateRequest templateRequest = message.getObject("templateRequest", DochaAdminInsuranceTemplateRequest.class);

        List<DochaAdminInsuranceTemplateResponse> responseDto = insuranceTemplateMapper.insuranceTemplateinfoDetail(templateRequest);

        message.addData("result", responseDto);

    }

    //endregion
    //endregion





    //region [ 차량 모델 ]
    /* 차량모델 등록 */
    @Override
    public void insertCarModelInfo(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        int res = carModelMapper.insertCarModelInfo(carModelDetailRequest);

        message.addData("res", res);
        message.addData("mdIdx", carModelDetailRequest.getMdIdx());
    }

    /* 차량모델 리스트 */
    @Override
    public void getCarModelList(ServiceMessage message) {
        DochaAdminCarModelRequest carModelRequest = new DochaAdminCarModelRequest();

        List<DochaAdminCarModelResponse> responseDto = carModelMapper.selectCarModelInfo(carModelRequest);

        message.addData("carModelInfoList", responseDto);

    }

    /* 차량모델 상세 */
    @Override
    public void selectCarModelDetail(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        List<DochaAdminCarModelDetailResponse> carModelDetailResponseList = carModelMapper.selectCarModelDetail(carModelDetailRequest);

        message.addData("result", carModelDetailResponseList);
    }

    /* 차량모델 수정 */
    @Override
    public void updateCarModelInfo(ServiceMessage message) {
        DochaAdminCarModelDetailRequest carModelDetailRequest = message.getObject("carModelDetailRequest", DochaAdminCarModelDetailRequest.class);

        int res = carModelMapper.updateCarModelInfo(carModelDetailRequest);

        message.addData("res", res);
    }
    // endregion





    //region [ 차량 속성 ]
    /* 차량속성 추가 : 국가 */
    @Override
    public void insertCarPropertyCountry(ServiceMessage message) {
        String value = message.getString("value","");
        DochaAdminCarPropertyRequest carPropertyRequest = DochaAdminCarPropertyRequest.builder()
                .rtCode("CN")
                .pCode("CN")
                .code(value)
                .build();

        int res = propertyMapper.insertCarProperty(carPropertyRequest);

        message.addData("res", res);
        message.addData("mdIdx", carPropertyRequest.getCodeIdx());
    }

    /* 차량 속성 리스트 : 국가 */
    @Override
    public void carCountryProperty(ServiceMessage message) {
        DochaAdminCarPropertyRequest carPropertyRequest = message.getObject("carPropertyRequest",DochaAdminCarPropertyRequest.class);

        List<DochaAdminCarPropertyResponse> carPropertyResponseList =  propertyMapper.selectCarCountryPropertyInfo(carPropertyRequest);

        message.addData("propertyList", carPropertyResponseList);

    }

    /* 차량속성 추가 : 제조사 */
    @Override
    public void insertCarPropertyManufacturer(ServiceMessage message) {
        String value = message.getString("value","");
        DochaAdminCarPropertyRequest carPropertyRequest = DochaAdminCarPropertyRequest.builder()
                .rtCode("CR")
                .pCode("MF")
                .code(value)
                .build();

        int res = propertyMapper.insertCarProperty(carPropertyRequest);

        message.addData("res", res);
        message.addData("mdIdx", carPropertyRequest.getCodeIdx());
    }

    /* 차량 속성 리스트 : 제조사 */
    @Override
    public void carManufacturerProperty(ServiceMessage message) {
        DochaAdminCarPropertyRequest carPropertyRequest = message.getObject("carPropertyRequest",DochaAdminCarPropertyRequest.class);

        List<DochaAdminCarPropertyResponse> carPropertyResponseList =  propertyMapper.selectCarCountryManufacturerInfo(carPropertyRequest);

        message.addData("propertyList", carPropertyResponseList);

    }

    /* 차량속성 추가 : 등급 */
    @Override
    public void insertCarPropertyCarType(ServiceMessage message) {
        String value = message.getString("value","");
        DochaAdminCarPropertyRequest carPropertyRequest = DochaAdminCarPropertyRequest.builder()
                .rtCode("CR")
                .pCode("CTY")
                .code(value)
                .build();

        int res = propertyMapper.insertCarProperty(carPropertyRequest);

        message.addData("res", res);
        message.addData("mdIdx", carPropertyRequest.getCodeIdx());
    }

    /* 차량 속성 리스트 : 등급 */
    @Override
    public void carCarTypeProperty(ServiceMessage message) {
        DochaAdminCarPropertyRequest carPropertyRequest = message.getObject("carPropertyRequest",DochaAdminCarPropertyRequest.class);

        List<DochaAdminCarPropertyResponse> carPropertyResponseList =  propertyMapper.selectCarTypePropertyInfo(carPropertyRequest);

        message.addData("propertyList", carPropertyResponseList);

    }

    /* 차량속성 추가 : 옵션 */
    @Override
    public void insertCarPropertyOption(ServiceMessage message) {
        String value = message.getString("value","");
        DochaAdminCarPropertyRequest carPropertyRequest = DochaAdminCarPropertyRequest.builder()
                .rtCode("CR")
                .pCode("OT")
                .code(value)
                .build();

        int res = propertyMapper.insertCarProperty(carPropertyRequest);

        message.addData("res", res);
        message.addData("mdIdx", carPropertyRequest.getCodeIdx());
    }

    /* 차량 속성 리스트 : 옵션 */
    @Override
    public void carOptionProperty(ServiceMessage message) {
        DochaAdminCarPropertyRequest carPropertyRequest = message.getObject("carPropertyRequest",DochaAdminCarPropertyRequest.class);

        List<DochaAdminCarPropertyResponse> carPropertyResponseList =  propertyMapper.selectCarOptionPropertyInfo(carPropertyRequest);

        message.addData("propertyList", carPropertyResponseList);
    }

    /* 차량속성 추가 : 연료 */
    @Override
    public void insertCarPropertyFuel(ServiceMessage message) {
        String value = message.getString("value","");
        DochaAdminCarPropertyRequest carPropertyRequest = DochaAdminCarPropertyRequest.builder()
                .rtCode("CR")
                .pCode("FL")
                .code(value)
                .build();

        int res = propertyMapper.insertCarProperty(carPropertyRequest);

        message.addData("res", res);
        message.addData("mdIdx", carPropertyRequest.getCodeIdx());
    }

    /* 차량 속성 리스트 : 연료 */
    @Override
    public void carFuelProperty(ServiceMessage message) {
        DochaAdminCarPropertyRequest carPropertyRequest = message.getObject("carPropertyRequest",DochaAdminCarPropertyRequest.class);

        List<DochaAdminCarPropertyResponse> carPropertyResponseList =  propertyMapper.selectCarFuelPropertyInfo(carPropertyRequest);

        message.addData("propertyList", carPropertyResponseList);
    }
    /* 속성 삭제 */
    @Override
    public void deleteProperty(ServiceMessage message) {
        String codeIdx = message.getString("codeIdx");

        int res = propertyMapper.deleteProperty(codeIdx);
    }
    //endregion





    //region [ 요금제 ]
    /* 기간 요금제 등록 */
    @Override
    public void insertPlanSettingDetail(ServiceMessage message) {
        DochaAdminPeriodPlanSettingDetailRequest periodPlanSettingDetailRequest = message.getObject("periodPlanSettingDetailRequest", DochaAdminPeriodPlanSettingDetailRequest.class);

        int res = periodPlanSettingMapper.insertPlanSettingDetail(periodPlanSettingDetailRequest);

        message.addData("res", res);
    }

    /* 기간 요금제 리스트  */
    @Override
    public void getPeriodPlanList(ServiceMessage message) {
        DochaAdminPeriodPlanSettingRequest reqParam = new DochaAdminPeriodPlanSettingRequest();

        List<DochaAdminPeriodPlanSettingResponse> responseDto = periodPlanSettingMapper.selectPeriodPlanInfo(reqParam);

        message.addData("periodList", responseDto);
    }

    /* 기간 요금제 상세 */
    @Override
    public void selectPeriodPlanDetail(ServiceMessage message) {
        DochaAdminPeriodPlanSettingDetailRequest periodPlanSettingDetailRequest = message.getObject("periodPlanSettingDetailRequest", DochaAdminPeriodPlanSettingDetailRequest.class);

        List<DochaAdminPeriodPlanSettingDetailResponse> periodPlanSettingDetailResponseList = periodPlanSettingMapper.selectPeriodPlanDetail(periodPlanSettingDetailRequest);

        message.addData("result", periodPlanSettingDetailResponseList);
    }

    /* 기간 요금제 수정 */
    @Override
    public void updatePeriodPlan(ServiceMessage message) {
        DochaAdminPeriodPlanSettingDetailRequest periodPlanSettingDetailRequest = message.getObject("periodPlanSettingDetailRequest", DochaAdminPeriodPlanSettingDetailRequest.class);

        int res = periodPlanSettingMapper.updatePlanSettingDetail(periodPlanSettingDetailRequest);

        message.addData("res", res);
    }

    /* 기본 요금제 등록 */
    @Override
    public void insertBasicPlanInfo(ServiceMessage message) {
        DochaAdminBaiscPlanDetailRequest baiscPlanDetailRequest = message.getObject("baiscPlanDetailRequest", DochaAdminBaiscPlanDetailRequest.class);

        int res = basicPlanMapper.insertBasicPlanInfo(baiscPlanDetailRequest);

        message.addData("res", res);
    }

    /* 기본 요금제 리스트 */
    @Override
    public void getBasicPlanList(ServiceMessage message) {
        DochaAdminBaiscPlanRequest baiscPlanRequest = new DochaAdminBaiscPlanRequest();

        List<DochaAdminBasicPlanResponse> basicPlanResponseList = basicPlanMapper.selectBasicPlan(baiscPlanRequest);

        message.addData("basicPlanList", basicPlanResponseList);
    }

    /* 기본 요금제 상세 */
    @Override
    public void selectbasicPlanDetail(ServiceMessage message) {
        DochaAdminBaiscPlanDetailRequest baiscPlanDetailRequest = message.getObject("baiscPlanDetailRequest", DochaAdminBaiscPlanDetailRequest.class);

        List<DochaAdminBasicPlanDetailResponse> basicPlanDetailResponseList = basicPlanMapper.selectBasicPlanDetail(baiscPlanDetailRequest);

        message.addData("result", basicPlanDetailResponseList);
    }

    /* 기본 요금제 수정 */
    @Override
    public void updateBasicPlanInfo(ServiceMessage message) {
        DochaAdminBaiscPlanDetailRequest baiscPlanDetailRequest = message.getObject("baiscPlanDetailRequest", DochaAdminBaiscPlanDetailRequest.class);

        int res = basicPlanMapper.updateBasicPlanInfo(baiscPlanDetailRequest);

        message.addData("res", res);
    }



    /* 보험템플릿 등록 */
    @Override
    public void insertInsuranceTemplate(ServiceMessage message) {
        DochaAdminInsuranceTemplateDetailRequest insuranceTemplateDetailRequest = message.getObject("insuranceTemplateDetailRequest", DochaAdminInsuranceTemplateDetailRequest.class);

        int res = insuranceTemplateMapper.insertInsuranceTemplate(insuranceTemplateDetailRequest);

        message.addData("res", res);
    }

    /* 보험템플릿 리스트 */
    @Override
    public void getInsuranceTemplateList(ServiceMessage message) {
        DochaAdminInsuranceTemplateRequest insuranceTemplateRequest = new DochaAdminInsuranceTemplateRequest();

        List<DochaAdminInsuranceTemplateResponse> insuranceTemplateResponseList = insuranceTemplateMapper.selectInsuranceTemplateInfo(insuranceTemplateRequest);

        message.addData("insuranceList", insuranceTemplateResponseList);
    }
    /* 기본 요금제 상세 */
    @Override
    public void InsuranceTemplateDetail(ServiceMessage message) {
        DochaAdminInsuranceTemplateRequest insuranceTemplateRequest = message.getObject("insuranceTemplateRequest", DochaAdminInsuranceTemplateRequest.class);

        List<DochaAdminInsuranceTemplateResponse> basicPlanDetailResponseList = insuranceTemplateMapper.insuranceTemplateinfoDetail(insuranceTemplateRequest);

        message.addData("result", basicPlanDetailResponseList);
    }

    /* 기본 요금제 수정 */
    @Override
    public void updateInsuranceTemplate(ServiceMessage message) {
        DochaAdminInsuranceTemplateDetailRequest insuranceTemplateDetailRequest = message.getObject("insuranceTemplateDetailRequest", DochaAdminInsuranceTemplateDetailRequest.class);



        int res = insuranceTemplateMapper.updateInsuranceTemplate(insuranceTemplateDetailRequest);

        message.addData("res", res);
    }











    /* 등록차량 상세 옵션 */
    @Override
    public void selectRegCarDetailOption(ServiceMessage message) {
        DochaAdminRegCarDetailRequest reqParam = message.getObject("reqParam", DochaAdminRegCarDetailRequest.class);

        List<DochaAdminRegCarMapper> regCarMapperList = regCarMapper.selectRegCarDetailOption(reqParam);

        message.addData("result", regCarMapperList);
    }

}
