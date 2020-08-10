package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.menu.CsdealAdminMenuTemplateResponse;
import com.ohdocha.admin.domain.admin.CsdealAdminAdminUserInfoRequest;
import com.ohdocha.admin.domain.admin.CsdealAdminAdminUserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminAdminUserInfoMntMapper {

	/*
	 * 관리자 리스트 조회
	 * */
	public List<CsdealAdminAdminUserInfoResponse> selectAdminUserInfo(CsdealAdminAdminUserInfoRequest reqParam);


	/*
	 * 관리자 상세
	 * */
	public CsdealAdminAdminUserInfoResponse selectAdminUserInfoDetail(CsdealAdminAdminUserInfoRequest reqParam);


	/*
	 * 관리자 업데이트
	 * */
	public int updateAdminUserInfoDetail(CsdealAdminAdminUserInfoRequest reqParam);


	/*
	 * 관리자 신규 저장
	 * */
	public int insertAdminUserInfo(CsdealAdminAdminUserInfoRequest reqParam);


	/*
	 * 관리자 메뉴템플릿 권한 조회
	 * */
	public List<CsdealAdminMenuTemplateResponse> selectMenuTemplateList();

}
