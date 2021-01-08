package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.dto.CheckGroupDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.vo.CheckGroupVO;

public interface CheckGroupService extends IService<CheckGroup> {


    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroupVO findCheckIteminfosByGroupId(int id);

    boolean saveUpdate(CheckGroupDTO checkGroupDTO);
}
