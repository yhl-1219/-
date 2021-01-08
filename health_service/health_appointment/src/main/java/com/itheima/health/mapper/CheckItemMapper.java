package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @author wangweili
 */
public interface CheckItemMapper extends BaseMapper<CheckItem> {

    PageResult findPage(QueryPageBean queryPageBean);
}
