package com.itheima.health.vo;


import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleVO extends Role {

    private List<Permission> permissionsList = new ArrayList<>(0);//对应权限集合
    private List<MenuVO> menuVOList = new ArrayList<>();
}
