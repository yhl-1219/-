package com.itheima.health.dto;

import com.itheima.health.pojo.Setmeal;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangweili 
 */
@Data
public class SetmealDTO extends Setmeal implements Serializable {

    private Integer[] checkgroupIds;

}
