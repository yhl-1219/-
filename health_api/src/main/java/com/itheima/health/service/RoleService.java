package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;

import java.util.List;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/19 7:46 下午
 */
public interface RoleService extends IService<Role> {

    PageResult findPage(QueryPageBean pageBean);

    List<Permission> findAllPermission();

    List<Menu> findAllMenu();

    Integer[] findPermissionInfoById(Integer id);

    Integer[] findMenuInfoById(Integer id);

    boolean addOrUpdateRole(Role role);

    boolean deleteRoleById(Integer id);

}
