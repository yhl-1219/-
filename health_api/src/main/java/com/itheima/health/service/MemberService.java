package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.ReportEntity;
import com.itheima.health.entity.ReportMemberCondition;
import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * @author wangweili
 */
public interface MemberService extends IService<Member> {

    Member findMemberByTelephone(String telephone);

    int saveMember(Member member);

    ReportEntity getMemberCount(ReportMemberCondition condition);

    List<Map<String,String>> getSetmealCount();

}
