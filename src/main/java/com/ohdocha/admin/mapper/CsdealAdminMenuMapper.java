package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.menu.CsdealAdminMenuRequest;
import com.ohdocha.admin.domain.menu.CsdealAdminMenuResponse;
import com.ohdocha.admin.domain.menu.CsdealAdminMenuTemplateRequest;
import com.ohdocha.admin.domain.menu.CsdealAdminMenuTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsdealAdminMenuMapper {

    public List<CsdealAdminMenuResponse> selectMenuInfoList(CsdealAdminMenuRequest reqParam);

    public int insertMenuTemplate(CsdealAdminMenuTemplateRequest reqParam);


    public List<CsdealAdminMenuTemplateResponse> selectMenuTemplateList(CsdealAdminMenuTemplateRequest reqParam);


    public List<CsdealAdminMenuResponse> selectMenuInfoAll();

}
