package com.itheima.health.vo;


import com.itheima.health.pojo.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangweili 
 */
@Data
public class UserVO extends User {

    /**
     * 对应角色集合
     */
    private List<RoleVO> roleVOList = new ArrayList<>(0);

}
