package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

public interface CreateMemberService extends IService<Member> {

    boolean saveMember(int id);

    List<Map<String,String>> findMemberBySex();

    List<Map<String,String>> findMemberByAge();

}
