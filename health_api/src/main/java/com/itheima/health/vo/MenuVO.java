package com.itheima.health.vo;


import com.itheima.health.pojo.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangweili 
 */
@Data
public class MenuVO extends Menu {

    /**
     * 子菜单集合
     */
    private List<MenuVO> children = new ArrayList<>();

}
