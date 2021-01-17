package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.vo.SetmealVO;

/**
 * @author wangweili 
 */
public interface SetmealService extends IService<Setmeal> {

    boolean saveUpdate(SetmealDTO setmealDTO);

    boolean delete(Integer id);

    PageResult findPage(QueryPageBean queryPageBean);

    int[] findGroupIdsBySetmealId(Integer id);

    void clearOssImg();

    Setmeal findSetMealDetailById(int id);
}
