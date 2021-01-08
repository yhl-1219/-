package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Member;

public interface MemberService extends IService<Member> {
    Member findMemberByTelephone(String telephone);

    int saveMember(Member member);
}
