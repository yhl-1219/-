package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.vo.SetmealVO;

public interface SetmealService extends IService<Setmeal> {
    boolean saveUpdate(SetmealDTO setmealDTO);

    PageResult findPage(QueryPageBean queryPageBean);

    void clearOssImg();

    SetmealVO findSetMealDetailById(int id);
}
