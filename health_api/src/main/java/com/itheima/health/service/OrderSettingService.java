package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author wangweili 
 */
public interface OrderSettingService extends IService<OrderSetting> {
    
    boolean importOrderSettings(List<String[]> readExcelList);

    Map findSettingData(int year, int month);

    boolean updateNumberByOrderDate(int number, String orderdate);

    int isOrderOkOrNot(String orderdate);

    void updateReservationsByOrderDate(String orderdate);

    void clearOrderSettingHistoryData();
}
