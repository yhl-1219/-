package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.OrderSettingMapper;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.date.DateUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangweili
 */
@Service
@Transactional
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

    @Override
    @SneakyThrows
    public boolean importOrderSettings(List<String[]> readExcelList) {
        List<OrderSetting> orderSettingImportList = changeStringToList(readExcelList);
        for (OrderSetting orderSetting : orderSettingImportList) {
            QueryWrapper<OrderSetting> wrapper = new QueryWrapper<>();
            System.out.println(DateUtils.parseDate2String(orderSetting.getOrderDate()));
            wrapper.eq("orderdate", DateUtils.parseDate2String(orderSetting.getOrderDate()));
            OrderSetting orderSettingIsOrNotExist = baseMapper.selectOne(wrapper);
            if (orderSettingIsOrNotExist != null) {
                orderSetting.setId(orderSettingIsOrNotExist.getId());
            }
        }
        saveOrUpdateBatch(orderSettingImportList);
        return true;
    }

    @Override
    @SneakyThrows
    public Map findSettingData(int year, int month) {
        String beginTime = year + "-" + month + "-1";
        Date newDate = DateUtils.parseString2Date(beginTime);
        Date lastDayOfMonth = DateUtils.getLastDayOfMonth(newDate);
        String endTime = DateUtils.parseDate2String(lastDayOfMonth);
        List<OrderSetting> orderSettings = baseMapper.findSettingDataByYearAndMonth(beginTime, endTime);
        Map<String, Object> map = new HashMap<>();
        for (OrderSetting orderSetting : orderSettings) {
            Date orderDate = orderSetting.getOrderDate();
            String date = DateUtils.parseDate2String(orderDate);
            HashMap orderSettingMap = new HashMap<>();
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            map.put(date, orderSettingMap);
        }
        return map;
    }

    @Override
    public boolean updateNumberByOrderDate(int number, String orderdate) {
        return false;
    }

    @Override
    public int isOrderOKorNot(String orderdate) {
        return 0;
    }

    @Override
    public void updateReservationsByOrderDate(String orderdate) {

    }

    private List<OrderSetting> changeStringToList(List<String[]> readExcelList) {
        if (readExcelList != null && readExcelList.size() != 0) {
            List<OrderSetting> list = new ArrayList<>();
            for (String[] strings : readExcelList) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setReservations(0);
                orderSetting.setNumber(Integer.parseInt(strings[1]));
                orderSetting.setOrderDate(DateUtils.parseString2Date(strings[0], "yyyy/MM/dd"));
                list.add(orderSetting);
            }
            return list;
        }
        return null;
    }
}
