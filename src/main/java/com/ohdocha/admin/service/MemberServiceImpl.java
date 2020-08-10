package com.ohdocha.admin.service;

import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MemberServiceImpl extends ServiceExtension implements MemberService {

    @Override
    public void getIntegratedMemberList(ServiceMessage message) {
    }

}
