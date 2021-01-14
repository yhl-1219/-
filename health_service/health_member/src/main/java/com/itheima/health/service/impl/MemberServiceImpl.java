package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.itheima.health.mapper.MemberMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Member findMemberByTelephone(String telephone) {
        QueryWrapper<Member> wrapper = new QueryWrapper();
        wrapper.eq("phonenumber", telephone);
        return getOne(wrapper);
    }

    @LcnTransaction
    @Override
    public int saveMember(Member member) {
        save(member);
        return 1;
    }
}
