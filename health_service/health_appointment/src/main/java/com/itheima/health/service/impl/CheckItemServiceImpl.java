package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.CheckItemMapper;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangweili
 */
@Service
@Transactional
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements CheckItemService {

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        Page<CheckItem> page = null;
        QueryWrapper<CheckItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_delete", 0);
        if (!StringUtils.isEmpty(pageBean.getQueryString())) {
            queryWrapper.like("name", pageBean.getQueryString()).or().like("code", pageBean.getQueryString());
        }
        page = page(new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize()), queryWrapper);
        return new PageResult(page.getTotal(), page.getRecords());
    }
}
