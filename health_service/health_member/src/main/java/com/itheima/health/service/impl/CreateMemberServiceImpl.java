package com.itheima.health.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.MemberMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.CreateMemberService;
import com.itheima.health.utils.idcard.IDCardsUtils;
import com.itheima.health.utils.idcard.RandomMemberUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CreateMemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements CreateMemberService {

    @Override
    public boolean saveMember(int id) {
        List<Member> members = new ArrayList<>();
        Member memberBuild = Member.builder().build();
        String a = "";
        int b = 0;
        for (int i = 0; i < id; i++) {
            memberBuild = Member.builder()
                    .name(RandomMemberUtil.getChineseName())
                    .idCard(a = RandomMemberUtil.getRandomIdCard())
                    .phoneNumber(RandomMemberUtil.getTel())
                    .password("123456")
                    .regTime(RandomMemberUtil.randomDate("2020-01-01", "2021-01-10"))
                    .birthday(RandomMemberUtil.randomDate("1970-01-01", "2021-01-10"))
                    .sex(Integer.parseInt(a.substring(16, 17)) % 2 == 0 ? "0" : "1")
                    .build();

            members.add(memberBuild);
        }
        return saveBatch(members, 2000);
    }

    @Override
    public List<Map<String, String>> findMemberBySex() {

        int b = 0;
        int g = 0;
        List<String> memberBySexList = baseMapper.findMemberByIdCard666();
        for (String memberIdCard : memberBySexList) {
            String s = IDCardsUtils.parseGender(memberIdCard);
            if ("男".equals(s)) {
                ++b;
            } else {
                ++g;
            }
        }
        int sum = b + g;
        Map bMap = new HashMap();
        Map gMap = new HashMap();

        bMap.put("value", b);
        bMap.put("name", "男会员占比");

        gMap.put("value", g);
        gMap.put("name", "女会员占比");

        // 判断sum是否为0，如果为0说明没有数据
        if (sum != 0) {
            List<Map<String, String>> list = new ArrayList<>();
            list.add(bMap);
            list.add(gMap);

            return list;
        } else {
            throw new RuntimeException("查询会员性别异常！");
        }
    }

    @Override
    public List<Map<String, String>> findMemberByAge() {


        int a = 0;  //0-18
        int b = 0;  //18-30
        int c = 0;  //30-45
        int d = 0;  //45-?
        List<String> memberBySexList = baseMapper.findMemberByIdCard666();
        for (String memberIdCard : memberBySexList) {
            int s = IDCardsUtils.parseAge(memberIdCard);
            if (s >= 0 && s < 18) {
                ++a;
            } else if (s >= 18 && s < 30) {
                ++b;
            } else if (s >= 30 && s < 45) {
                ++c;
            } else if (s > 45) {
                ++d;
            }
        }
        int sum = a + b + c + d;
        Map aMap = new HashMap();
        Map bMap = new HashMap();
        Map cMap = new HashMap();
        Map dMap = new HashMap();
        aMap.put("value", a);
        aMap.put("name", "年龄0-18占比");
        bMap.put("value", b);
        bMap.put("name", "年龄18-30占比");
        cMap.put("value", c);
        cMap.put("name", "年龄30-45占比");
        dMap.put("value", d);
        dMap.put("name", "年龄大于45占比");
        // 判断sum是否为0，如果为0说明没有数据
        if (sum != 0) {
            List<Map<String, String>> list = new ArrayList<>();
            list.add(aMap);
            list.add(bMap);
            list.add(cMap);
            list.add(dMap);
            return list;
        } else {
            throw new RuntimeException("查询会员年龄异常！");
        }

    }
}
