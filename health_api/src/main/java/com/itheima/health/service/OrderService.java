package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.pojo.Order;

import java.util.Map;

public interface OrderService extends IService<Order> {
    Order add(Map map);

    Map findOrderInfoById(int id);
}
