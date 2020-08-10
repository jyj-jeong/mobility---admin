package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.calculateMnt.CsdealAdminCalculateRequest;
import com.ohdocha.admin.domain.calculateMnt.CsdealAdminCalculateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminCalculateMapper {

	public List<CsdealAdminCalculateResponse> selectCalculateInfoList(CsdealAdminCalculateRequest reqParam);
	public List<CsdealAdminCalculateResponse> selectCalculateDateCompanyList(CsdealAdminCalculateRequest reqParam);
	public List<CsdealAdminCalculateResponse> selectCalculateDateReserveList(CsdealAdminCalculateRequest reqParam);
	public List<CsdealAdminCalculateResponse> selectCalculateAccountsExpectedInfo(CsdealAdminCalculateRequest reqParam);
	public int insertRentAccounts(CsdealAdminCalculateRequest reqParam);

}
