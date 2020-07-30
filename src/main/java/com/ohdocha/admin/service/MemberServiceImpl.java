package com.ohdocha.admin.service;

import com.ohdocha.admin.Mapper.CsdealAdminLoginMapper;
import com.ohdocha.admin.domain.Member;
import com.ohdocha.admin.domain.MemberRepository;
import com.ohdocha.admin.util.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MemberServiceImpl extends ServiceExtension implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void getIntegratedMemberList(ServiceMessage message) {
        List<Member> IntegratedMemberList = memberRepository.findAll();

        message.addData("IntegratedMemberList", IntegratedMemberList);
    }

}
