package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.dto.CheckGroupDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.vo.CheckGroupVO;

/**
 * @author wangweili 
 */
public interface CheckGroupService extends IService<CheckGroup> {

    /**
     * 查询分页
     * 
     * @param queryPageBean 分页数据
     * @return PageResult
     */
    PageResult findPage(QueryPageBean queryPageBean);

    boolean add(CheckGroupDTO checkGroupDTO);

    CheckGroupVO findCheckIteminfosByGroupId(int id);

    boolean saveUpdate(CheckGroupDTO checkGroupDTO);
}
