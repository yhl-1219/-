package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

public interface CheckItemService  extends IService<CheckItem> {
    PageResult findPage(QueryPageBean queryPageBean);
}
