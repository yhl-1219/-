package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.itheima.health.entity.ReportEntity;
import com.itheima.health.entity.ReportMemberCondition;
import com.itheima.health.mapper.MemberMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import com.itheima.health.utils.date.DateUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wangweili
 */
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

    @Override
    @SneakyThrows
    public ReportEntity getMemberCount(ReportMemberCondition condition) {
        String beginTime = DateUtils.parseDate2String(condition.getBeginTime());
        String endTime = DateUtils.parseDate2String(condition.getEndTime());
        ArrayList<HashMap<String, Object>> resultList = baseMapper.getMemberCount(beginTime, endTime, condition.getType());
        int size = resultList.size();
//        for (int i = size - 1; i >= 0; i--) {
//            HashMap<String, Object> map1 = resultList.get(i - 1);
//            HashMap<String, Object> map2 = resultList.get(i);
//            String name1 = (String) map1.get("name");
//            String name2 = (String) map2.get("name");
//            if (split(name2) - split(name1) > 1) {
//                switch (condition.getType()) {
//                    case 2:
//                        name2.
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
        //这里缺失了查表数据为0时会被笛卡尔积消除的情况。。。逻辑修补一下
        ArrayList<Object> name = new ArrayList<>();
        ArrayList<Object> member = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            name.add(resultList.get(i).get("name"));
            member.add(resultList.get(i).get("member"));
        }
        ReportEntity entity = new ReportEntity();
        entity.setName(name);
        entity.setMember(member);
        return entity;
    }

    @Override
    public List<Map<String, String>> getSetmealCount() {
        return baseMapper.getSetmealCount();
    }

    private Integer split(String name) {
        String[] splitResult = name.split("-");
        String var1 = splitResult[splitResult.length - 1];
        return Integer.valueOf(var1.replace("周", ""));
    }

}
