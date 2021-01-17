package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Member;

import java.util.HashMap;
import java.util.Map;

public interface MemberService extends IService<Member> {
    Member findMemberByTelephone(String telephone);

    int saveMember(Member member);

    HashMap<String, Map<String, Object>> getMemberCount(Integer year,Integer month);
}
