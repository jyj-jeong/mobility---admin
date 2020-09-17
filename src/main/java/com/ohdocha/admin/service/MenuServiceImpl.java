package com.ohdocha.admin.service;

import com.ohdocha.admin.domain.menu.DochaAdminMenuRequest;
import com.ohdocha.admin.domain.menu.DochaAdminMenuResponse;
import com.ohdocha.admin.mapper.DochaAdminMenuMapper;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MenuServiceImpl extends ServiceExtension implements MenuService {

    private final DochaAdminMenuMapper menuMapper;

    @Override
    public void getMenuList(ServiceMessage message) {
        DochaAdminMenuRequest menuRequest = message.getObject("menuRequest", DochaAdminMenuRequest.class);

        List<DochaAdminMenuResponse> menuResponseList = menuMapper.selectMenuInfoList(menuRequest);

        message.addData("result", menuResponseList);
    }

}
