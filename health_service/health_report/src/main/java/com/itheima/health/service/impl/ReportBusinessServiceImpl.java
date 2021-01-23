package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.ReportBusinessMapper;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.ReportBusinessService;
import com.itheima.health.utils.date.DateUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Service;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
@Transactional
public class ReportBusinessServiceImpl extends ServiceImpl<ReportBusinessMapper, Member> implements ReportBusinessService {

    @SneakyThrows
    @Override
    public Map findReportBusinessData() {
        //创建map,存入统计数据
        Map map = new HashMap();
        //今日新增会员数
        Integer todayNewMember = baseMapper.findTodayNewMember();
        //总会员数
        Integer totalMember = baseMapper.selectCount(null);
        //本周新增会员数
        Integer thisWeekNewMember = baseMapper.findThisWeekNewMember();
        //本月新增会员数量
        Integer thisMonthNewMember = baseMapper.findThisMonthNewMember();
        Integer todayOrderNumber = baseMapper.findTodayOrderNumber();
        //查询今日到诊数
        Integer todayVisitsNumber = baseMapper.findTodayVisitsNumber();
        //查询本周预约数
        Integer thisWeekOrderNumber = baseMapper.findThisWeekOrderNumber();
        //查询本周到诊数
        Integer thisWeekVisitsNumber = baseMapper.findThisWeekVisitsNumber();
        //查询本月预约数
        Integer thisMonthOrderNumber = baseMapper.findThisMonthOrderNumber();
        //本月到诊数
        Integer thisMonthVisitsNumber = baseMapper.findThisMonthVisitsNumber();
        List<Map> hotSetmeals = baseMapper.findHotSetmeal();
        //创建一个集合,放入热门套餐
        List<Map> list = new ArrayList();
        //遍历热门套餐集合
        for (int i = 0; i < hotSetmeals.size(); i++) {
            if (i <= 4) {
                list.add(hotSetmeals.get(i));
            }
        }

        //将查询数据放到map中
        map.put("reportDate", DateUtils.parseDate2String(DateUtils.getToday(), "yyyy-MM-dd"));
        map.put("todayNewMember", todayNewMember);
        map.put("totalMember", totalMember);
        map.put("thisWeekNewMember", thisWeekNewMember);
        map.put("thisMonthNewMember", thisMonthNewMember);
        map.put("todayOrderNumber", todayOrderNumber);
        map.put("todayVisitsNumber", todayVisitsNumber);
        map.put("thisWeekOrderNumber", thisWeekOrderNumber);
        map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber", thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        map.put("hotSetmeal", list);
        return map;
    }
}
