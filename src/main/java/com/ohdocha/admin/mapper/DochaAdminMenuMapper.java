package com.ohdocha.admin.mapper;

import com.ohdocha.admin.domain.menu.DochaAdminMenuRequest;
import com.ohdocha.admin.domain.menu.DochaAdminMenuResponse;
import com.ohdocha.admin.domain.menu.DochaAdminMenuTemplateRequest;
import com.ohdocha.admin.domain.menu.DochaAdminMenuTemplateResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DochaAdminMenuMapper {

    public List<DochaAdminMenuResponse> selectMenuInfoList(DochaAdminMenuRequest reqParam);

    public int insertMenuTemplate(DochaAdminMenuTemplateRequest reqParam);


    public List<DochaAdminMenuTemplateResponse> selectMenuTemplateList(DochaAdminMenuTemplateRequest reqParam);


    public List<DochaAdminMenuResponse> selectMenuInfoAll();

}
