package com.itheima.health.vo;


import com.itheima.health.pojo.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangweili
 */
@Data
public class MenuVO extends Menu {

    /**
     * 子菜单集合
     */
    private List<MenuVO> children = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(children, menuVO.children);
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "children=" + children +
                "} " + super.toString();
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), children);
    }

}
