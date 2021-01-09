package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

/**
 * @author wangweili
 */
public interface CheckItemService  extends IService<CheckItem> {

    /**
     * 分页查询
     *
     * @param pageBean 分页查询Bean
     * @return 结果
     */
    PageResult findPage(QueryPageBean pageBean);

}
