package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangweili 
 */
public interface OrderService extends IService<Order> {

    Order add(Map<String, String> map);

    Map findOrderInfoById(int id);

    Boolean updateState(Map param);

    Boolean findOrderStateById(Integer oid);

    void updateReservationsByOrderDate(Integer id);

}
