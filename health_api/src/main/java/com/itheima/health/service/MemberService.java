package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangweili
 */
public interface MemberService extends IService<Member> {

    Member findMemberByTelephone(String telephone);

    int saveMember(Member member);

    HashMap<String, Map<String, Object>> getMemberCount(Integer year,Integer month);

    List<Map<String,String>> getSetmealCount();
}
