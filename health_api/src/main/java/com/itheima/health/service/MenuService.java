package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;

import java.util.Map;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/22 11:06 上午
 */
public interface MenuService extends IService<Menu> {

    Map findAllMenuByUsername(String username);

    PageResult findPage(QueryPageBean pageBean);

    boolean addOrUpdateMenu(Menu menu);

    boolean deleteMenuById(Integer id);

}