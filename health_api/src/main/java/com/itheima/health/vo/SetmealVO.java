package com.itheima.health.vo;


import com.itheima.health.pojo.Setmeal;
import lombok.Data;

import java.util.List;

/**
 * @author wangweili 
 */
@Data
public class SetmealVO extends Setmeal {

    private List<CheckGroupVO> checkGroupVOList;

}
