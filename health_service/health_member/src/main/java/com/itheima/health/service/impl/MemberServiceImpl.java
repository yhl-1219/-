package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.itheima.health.mapper.MemberMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import com.itheima.health.utils.date.DateUtils;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wangweili 
 */
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    
    private static final Integer ONE_YEAR = 12;

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

    @Override
    public HashMap<String, Map<String, Object>> getMemberCount(Integer year, Integer month) {
        String nowDate = year + "-" + month + "-" + "01";
        HashMap<String, Map<String, Object>> map = new HashMap<>(18);
        Integer nowYear = 0;
        Integer nowMonth = 0;
        for (int i = 0; i < ONE_YEAR; i++) {
            Map<String, Object> count = baseMapper.getMemberCount(i, nowDate);
            String s = "";
            if (count == null) {
                if (month + i > ONE_YEAR) {
                    nowYear = year + 1;
                    nowMonth = month + i - ONE_YEAR;
                    s = nowYear + "-" + nowMonth;
                } else {
                    s = year + "-" + (month + i);
                }
                count = new HashMap<String, Object>(4);
                count.put("name", s);
                count.put("member", 0);
            }
            map.put("th" + (i + 1) + "month", count);
        }
        return map;
    }

    @Override
    public List<Map<String, String>> getSetmealCount() {
        return baseMapper.getSetmealCount();
    }


}
