package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.OrderMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.service.MemberService;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.date.DateUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Reference
    private OrderSettingService orderSettingService;

    @Reference
    private MemberService memberService;

    @Override
    public Order add(Map<String, String> map) {
        String telephone = map.get("telephone");
        String orderDate = map.get("orderDate");
        if (orderSettingService.isOrderOKorNot(orderDate) <= 0) {
            throw new RuntimeException("预约已满，请选择其他日期");
        }
        Member memberFountByTelephone = memberService.findMemberByTelephone(telephone);
        if (memberFountByTelephone == null) {
            memberFountByTelephone = Member.builder()
                    .name(map.get("name"))
                    .sex(map.get("sex"))
                    .idCard(map.get("idCard"))
                    .phoneNumber(telephone)
                    .regTime(new Date())
                    .build();
            memberService.save(memberFountByTelephone);
        }
        if (baseMapper.findOrderBySetmealIdAndOrderDateAndMemberId(Integer.valueOf(map.get("setmealId")), orderDate, memberFountByTelephone.getId()) > 0) {
            throw new RuntimeException("您已经预约过，请择日再约");
        }
        orderSettingService.updateReservationsByOrderDate(orderDate);
        Order order = Order.builder()
                .orderDate(DateUtils.parseString2Date(orderDate))
                .memberId(memberFountByTelephone.getId())
                .setmealId(Integer.valueOf(map.get("setmealId")))
                .orderType(map.get("orderType"))
                .orderStatus("未到诊")
                .build();
        save(order);
        return order;
    }

    @Override
    public Map findOrderInfoById(int id) {
        return null;
    }
}
